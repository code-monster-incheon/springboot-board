package com.algo.inc.domain.product;

import com.algo.inc.domain.BaseTimeEntity;
import com.algo.inc.domain.basket.Basket;
import com.algo.inc.domain.order.ProductOrder;
import com.algo.inc.domain.orderProductMap.OrderProductMap;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@ToString(exclude = {"basketList", "productOrderList"})
public class Product extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int quantity;
    private boolean enabled;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
    private List<Basket> basketList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderProductMap> orderProductMapList;
}
