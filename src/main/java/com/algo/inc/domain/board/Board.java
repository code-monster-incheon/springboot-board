package com.algo.inc.domain.board;

import com.algo.inc.domain.BaseTimeEntity;
import com.algo.inc.domain.reply.Reply;
import com.algo.inc.domain.member.Member;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@ToString(exclude = {"member", "replyList"})
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    private String content;

    // 다대일
    @ManyToOne
    private Member member;
    private String password;
    private int view;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "board")
    private List<Reply> replyList;

    @Builder
    public Board(String title, String content)
    {
        this.title = title;
        this.content =content;
    }

}
