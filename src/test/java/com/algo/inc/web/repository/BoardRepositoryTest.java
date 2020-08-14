package com.algo.inc.web.repository;

import com.algo.inc.domain.board.Board;
import com.algo.inc.domain.member.Member;
import com.algo.inc.domain.member.Role;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    public void init()
    {
        Member member = new Member();
        member.setId("이효리");
        member.setPassword(passwordEncoder.encode("manstqw"));
        member.setName("이효리");
        member.setRole(Role.ROLE_MANAGER);
        member.setEnabled(true);
        memberRepository.save(member);


        Member member2 = new Member();
        member2.setId("유재석");
        member2.setPassword(passwordEncoder.encode("manstqw"));
        member2.setName("유재석");
        member2.setRole(Role.ROLE_MANAGER);
        member2.setEnabled(true);
        memberRepository.save(member2);

        for (int i = 1; i <= 13; i++)
        {
            Board board = new Board();
            board.setMember(member);
            board.setTitle(member.getName() + "가 등록한 게시글 "+ i);
            board.setContent(member.getName() + " 가 등록한 게시글" + i );
            boardRepository.save(board);
        }

        for (int i = 1; i <= 13; i++)
        {
            Board board = new Board();
            board.setMember(member2);
            board.setTitle(member2.getName() + "가 등록한 게시글 "+ i);
            board.setContent(member2.getName() + " 가 등록한 게시글" + i );
            boardRepository.save(board);
        }


    }
    @Test
    public void 유저의_정보를_가져온다()
    {

    }
}
