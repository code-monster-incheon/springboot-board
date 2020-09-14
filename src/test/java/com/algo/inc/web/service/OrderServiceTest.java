package com.algo.inc.web.service;

import com.algo.inc.web.dto.orders.OrderRequestParam;
import org.aspectj.weaver.ast.Or;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    @Transactional
    public void 상품_2개_주문_테스트() throws Exception
    {
        List<OrderRequestParam> param = new ArrayList<>();

        OrderRequestParam orderRequestParam1 = new OrderRequestParam(1L, 2);
        OrderRequestParam orderRequestParam2 = new OrderRequestParam(2L, 2);

        param.add(orderRequestParam1);
        param.add(orderRequestParam2);

        orderService.orderProducts(param);
    }
}
