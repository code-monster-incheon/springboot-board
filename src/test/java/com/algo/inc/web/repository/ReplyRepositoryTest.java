package com.algo.inc.web.repository;

import com.algo.inc.domain.board.Board;
import com.algo.inc.domain.reply.Reply;
import com.algo.inc.domain.member.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReplyRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void replyTest(){
        Member member1 = new Member();
        Board board = new Board();

        member1.setAccount("김종범");
        memberRepository.save(member1);

        board.setMember(member1);
        board.setTitle("QWQQEQWEQWEEQW");
        boardRepository.save(board);
String name ="DDDD";
        Member newMember = new Member();
        newMember.setAccount(name);
        memberRepository.save(newMember);
        Reply reply = new Reply();
        reply.setBoard(board);

        reply.setMember(newMember);
        replyRepository.save(reply);
        List<Reply> r = replyRepository.findAll();

        r.forEach(System.out::println);
        Reply reply1 = replyRepository.findById(1L)
                .orElseThrow(()-> new IllegalArgumentException("없음"));

        assertThat(reply1.getMember().getAccount()).isEqualTo(name);


    }


}
