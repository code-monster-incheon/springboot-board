package com.algo.inc.domain.basket;

import com.algo.inc.domain.BaseTimeEntity;
import com.algo.inc.domain.member.Member;
import com.algo.inc.domain.product.Product;
import com.algo.inc.domain.reply.Reply;
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
@ToString(exclude = {"member", "product"})
public class Basket extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name="product_id", nullable = false)
    private Product product;
}
