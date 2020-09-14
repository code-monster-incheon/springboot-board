package com.algo.inc.domain.member;


import com.algo.inc.domain.BaseTimeEntity;
import com.algo.inc.domain.basket.Basket;
import com.algo.inc.domain.board.Board;
import com.algo.inc.domain.order.ProductOrder;
import com.algo.inc.domain.reply.Reply;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@ToString(exclude = {"boardList", "replyList", "basketList", "productOrderList"})
@Accessors(chain = true)
public class Member extends BaseTimeEntity {

    @Id
    private String id;
    private String name;
    private String email;
    private String sex;
    private String password;
    private String phoneNumber;
    private String isAuthEmail;
    private boolean enabled;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="member")
    private List<MemberRole> roles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.ALL)
    private List<Board> boardList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.ALL)
    private List<Reply> replyList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.ALL)
    private List<Basket> basketList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.ALL)
    private List<ProductOrder> productOrderList;

    @Builder
    public Member(String id, String name, String password, List<MemberRole> roles)
    {
        this.id = id;
        this.password = password;
        this.name = name;
        this.roles = roles;
    }
}
