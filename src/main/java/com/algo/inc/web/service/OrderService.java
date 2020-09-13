package com.algo.inc.web.service;

import com.algo.inc.domain.order.ProductOrder;
import com.algo.inc.domain.orderProductMap.OrderProductMap;
import com.algo.inc.domain.product.Product;
import com.algo.inc.web.dto.orders.OrderRequestParam;
import com.algo.inc.web.repository.MemberRepository;
import com.algo.inc.web.repository.OrderRepository;
import com.algo.inc.web.repository.ProductOrderRepository;
import com.algo.inc.web.repository.ProductRepository;
import com.querydsl.core.types.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final ProductOrderRepository productOrderRepository;

    // TODO : 추후 반드시 리팩토링 진행해야 한다.
    //  지금은 자바 빈 패턴
    @Transactional
    public void orderProducts(List<OrderRequestParam> param) throws Exception
    {
        List<OrderProductMap> orderProductMapLists = new ArrayList<>();

        ProductOrder productOrder = new ProductOrder();
        productOrder.setMember(memberRepository.findById("TestUser").get());

        for (OrderRequestParam order : param)
        {
           Product product = productRepository.findById(order.getProdId())
                            .orElseThrow(()-> new IllegalArgumentException("없는 상품 ID 입니다."));

           if (order.getCnt() > product.getQuantity())
           {
               // 주문한 상품의 개수가 기존 상품의 개수보다 많으면 에러 날림
               throw new Exception(order.getProdId() + " 상품 재고 부족");
           }
           else
           {
               // 주문한 상품 개수만큼 깐다.
               product.setQuantity(product.getQuantity() - order.getCnt());
           }
           // 상품을 하나씩 OrderProdctMap에 mapping
           OrderProductMap orderProductMap = new OrderProductMap();
           orderProductMap.setProduct(product);
           orderProductMap.setProductOrder(productOrder);

           // 상품 개수
           orderProductMap.setCnt(order.getCnt());

           // OrderProductMap 형 객체 리스트에 하나씩 넣는다.
           orderProductMapLists.add(orderProductMap);
        }

        productOrder.setOrderProductMapList(orderProductMapLists);

        // 한번에 save 하여 orderProductMap 에 대한 save를 따로 하지 않는다. cascade= CasacadeType.ALL
        productOrderRepository.save(productOrder);
    }
}
