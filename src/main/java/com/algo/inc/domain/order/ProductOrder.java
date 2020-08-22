package com.algo.inc.domain.order;

import com.algo.inc.domain.BaseTimeEntity;
import com.algo.inc.domain.member.Member;
import com.algo.inc.domain.orderProductMap.OrderProductMap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@ToString(exclude = {"member", "orderProductMapList"})
public class ProductOrder extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="member_id", nullable = false)
    private Member member;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productOrder", cascade = CascadeType.ALL)
    private List<OrderProductMap> orderProductMapList;
}
