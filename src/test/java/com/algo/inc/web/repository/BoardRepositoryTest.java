package com.algo.inc.web.repository;

import com.algo.inc.domain.member.Member;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

    @Before
    public void init()
    {

    }
    @Test
    public void 유저의_정보를_가져온다()
    {

        Member member = memberRepository.findById()
    }
}
