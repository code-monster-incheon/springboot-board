package com.algo.inc.domain.reply;

import com.algo.inc.domain.BaseTimeEntity;
import com.algo.inc.domain.board.Board;
import com.algo.inc.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    @JoinColumn(name="board_id", nullable = false) // 참조하고 있는 board0가 항상 조회되게 강제함(nullable= false)
    private Board board;
    @ManyToOne
    @JoinColumn(name="member_id", nullable = false) // 참조하고 있는 member가 항상 조회되게 강제함(nullable= false)
    private Member member;

}
