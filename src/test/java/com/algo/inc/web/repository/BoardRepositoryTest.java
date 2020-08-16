package com.algo.inc.web.repository;

import com.algo.inc.domain.board.Board;
import com.algo.inc.domain.member.Member;
import com.algo.inc.domain.member.Role;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void initMemberAndBoard()
    {
        List<Member> initMembers = Lists.newArrayList(
                Member.builder()
                        .id("spring")
                        .name("spring")
                        .password(passwordEncoder.encode("spring"))
                        .role(Role.ROLE_ADMIN)
                        .build()
                ,
                Member.builder()
                        .id("java")
                        .name("java")
                        .password(passwordEncoder.encode("java"))
                        .role(Role.ROLE_MEMBER)
                        .build()
                ,
                Member.builder()
                        .id("jpa")
                        .name("jpa")
                        .password(passwordEncoder.encode("jpa"))
                        .role(Role.ROLE_MEMBER)
                        .build()
                ,
                Member.builder()
                        .id("boot")
                        .name("boot")
                        .password(passwordEncoder.encode("boot"))
                        .role(Role.ROLE_MANAGER)
                        .build()
        );

        initMembers.forEach(member -> memberRepository.save(member));

        for (int i = 1; i <= 4; i++)
        {
            Member member = initMembers.get(i-1);
            String title = "칼럼 " + i;

            member.setBoardList(Lists.newArrayList(
                    Board.builder().title(title).content("content" + i).member(member).build(),
                    Board.builder().title(title).content("content" + i).member(member).build(),
                    Board.builder().title(title).content("content" + i).member(member).build(),
                    Board.builder().title(title).content("content" + i).member(member).build()
            ));

            memberRepository.save(member);
        }
    }
}
