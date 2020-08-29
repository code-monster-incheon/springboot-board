package com.algo.inc.domain.reply;

import com.algo.inc.domain.BaseTimeEntity;
import com.algo.inc.domain.board.Board;
import com.algo.inc.domain.member.Member;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
@ToString(exclude = {"board", "member"})
public class Reply extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @ManyToOne
    @JoinColumn(name="board_id") // 참조하고 있는 board0가 항상 조회되게 강제함(nullable= false)
    private Board board;
    @ManyToOne
    @JoinColumn(name="member_id") // 참조하고 있는 member가 항상 조회되게 강제함(nullable= false)
    private Member member;

    @Builder
    public Reply(String content, Board board,  Member member)
    {
        this.member = member;
        this.content = content;
        this.board = board;
    }

}
