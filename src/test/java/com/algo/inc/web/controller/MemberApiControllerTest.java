package com.algo.inc.web.controller;

import com.algo.inc.domain.member.Member;
import com.algo.inc.domain.member.Role;
import com.algo.inc.web.repository.MemberRepository;
import org.hibernate.query.ImmutableEntityUpdateQueryHandlingMode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class MemberApiControllerTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void TestUser()
    {
        Member member = new Member();
        member.setId("TestUser");
        member.setPassword("1111");
        member.setEmail("test@test.com");
        member.setRole(Role.ROLE_ADMIN);
        member.setEnabled(true);
        memberRepository.save(member);
    }
}
