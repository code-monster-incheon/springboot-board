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
    @Column(columnDefinition = "TEXT")
    private String content;

    // 다대일
    @ManyToOne
    @JoinColumn(name="member_id", nullable = false)
    private Member member;

    @Column(updatable = false)
    private int view = 0;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "board", cascade = CascadeType.ALL)
    private List<Reply> replyList;

    @Builder
    public Board(String title, String content, Member member)
    {
        this.title = title;
        this.content =content;
        this.member = member;
    }

}
