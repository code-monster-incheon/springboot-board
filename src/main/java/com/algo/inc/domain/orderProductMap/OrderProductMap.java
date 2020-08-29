package com.algo.inc.domain.orderProductMap;

import com.algo.inc.domain.BaseTimeEntity;
import com.algo.inc.domain.order.ProductOrder;
import com.algo.inc.domain.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
@ToString(exclude = {"productOrder", "product"})
public class OrderProductMap extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_order_id", nullable = false)
    private ProductOrder productOrder;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
