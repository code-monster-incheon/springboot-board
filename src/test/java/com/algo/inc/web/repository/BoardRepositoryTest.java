package com.algo.inc.web.repository;

import com.algo.inc.domain.board.Board;
import com.algo.inc.domain.member.Member;
import com.algo.inc.domain.member.MemberRole;
import com.algo.inc.domain.member.Role;
import com.algo.inc.domain.reply.Reply;
import lombok.extern.java.Log;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@Log
@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomCrudRepository customCrudRepository;

    @Before
    public void initTestMember()
    {
        Member member = new Member();
        member.setId("TestUser");
        member.setEnabled(true);
        member.setPassword(passwordEncoder.encode("TestUser"));
        MemberRole memberRole = new MemberRole();
        memberRole.setRoleName(Role.ADMIM.getRoleName());
        member.setRoles(Lists.newArrayList(memberRole));
        member.setReplyList(Lists.newArrayList());
        member.setEmail("test@test.com");
        member.setName("TestUser");
        memberRepository.save(member);
    }

    @Test
    public void initMemberAndBoard()
    {
        MemberRole memberRole = new MemberRole();
        memberRole.setRoleName(Role.ADMIM.getRoleName());
        List<MemberRole> roles = Lists.newArrayList(memberRole);
        List<Member> initMembers = Lists.newArrayList(
                Member.builder()
                        .id("spring")
                        .name("spring")
                        .password(passwordEncoder.encode("spring"))
                        .roles(roles)
                        .build()
                ,
                Member.builder()
                        .id("java")
                        .name("java")
                        .password(passwordEncoder.encode("java"))
                        .roles(roles)
                        .build()
                ,
                Member.builder()
                        .id("jpa")
                        .name("jpa")
                        .password(passwordEncoder.encode("jpa"))
                        .roles(roles)
                        .build()
                ,
                Member.builder()
                        .id("boot")
                        .name("boot")
                        .password(passwordEncoder.encode("boot"))
                        .roles(roles)
                        .build()
        );

        initMembers.forEach(member -> memberRepository.save(member));

        for (int i = 1; i <= 4; i++)
        {
            Member member = initMembers.get(i-1);
            String title = "칼럼 ";
            int cnt =1;

            member.setBoardList(Lists.newArrayList(
                    initReply(Board.builder().title(title + cnt).content("content" + cnt++).member(member).build()),
                    initReply(Board.builder().title(title + cnt).content("content" + cnt++).member(member).build()),
                    initReply(Board.builder().title(title + cnt).content("content" + cnt++).member(member).build()),
                    initReply(Board.builder().title(title + cnt).content("content" + cnt++).member(member).build()),
                    initReply(Board.builder().title(title + cnt).content("content" + cnt++).member(member).build()),
                    initReply(Board.builder().title(title + cnt).content("content" + cnt++).member(member).build()),
                    initReply(Board.builder().title(title + cnt).content("content" + cnt++).member(member).build()),
                    initReply(Board.builder().title(title + cnt).content("content" + cnt++).member(member).build()),
                    initReply(Board.builder().title(title + cnt).content("content" + cnt++).member(member).build()),
                    initReply(Board.builder().title(title + cnt).content("content" + cnt++).member(member).build()),
                    initReply(Board.builder().title(title + cnt).content("content" + cnt++).member(member).build()),
                    initReply(Board.builder().title(title + cnt).content("content" + cnt++).member(member).build()),
                    initReply(Board.builder().title(title + cnt).content("content" + cnt++).member(member).build()),
                    initReply(Board.builder().title(title + cnt).content("content" + cnt++).member(member).build()),
                    initReply(Board.builder().title(title + cnt).content("content" + cnt++).member(member).build()),
                    initReply(Board.builder().title(title + cnt).content("content" + cnt++).member(member).build()),
                    initReply(Board.builder().title(title + cnt).content("content" + cnt++).member(member).build()),
                    initReply(Board.builder().title(title + cnt).content("content" + cnt++).member(member).build()),
                    initReply(Board.builder().title(title + cnt).content("content" + cnt++).member(member).build()),
                    initReply(Board.builder().title(title + cnt).content("content" + cnt++).member(member).build()),
                    initReply(Board.builder().title(title + cnt).content("content" + cnt++).member(member).build()),
                    initReply(Board.builder().title(title + cnt).content("content" + cnt++).member(member).build()),
                    initReply(Board.builder().title(title + cnt).content("content" + cnt++).member(member).build()),
                    initReply(Board.builder().title(title + cnt).content("content" + cnt++).member(member).build()),
                    initReply(Board.builder().title(title + cnt).content("content" + cnt++).member(member).build()),
                    initReply(Board.builder().title(title + cnt).content("content" + cnt++).member(member).build()),
                    initReply(Board.builder().title(title + cnt).content("content" + cnt++).member(member).build()),
                    initReply(Board.builder().title(title + cnt).content("content" + cnt++).member(member).build()),
                    initReply(Board.builder().title(title + cnt).content("content" + cnt++).member(member).build()),
                    initReply(Board.builder().title(title + cnt).content("content" + cnt++).member(member).build())
            ));

            memberRepository.save(member);
        }
    }

    private Board initReply(Board board)
    {
        Member member = memberRepository.findById("TestUser").get();
        List<Reply> replyList = Lists.newArrayList(
                Reply.builder().content("첫 번째 댓글")
                        .member(member)
                        .board(board)
                        .build(),

                Reply.builder().content("두 번째 댓글")
                        .member(member)
                        .board(board)
                        .build(),

                Reply.builder().content("세 번째 댓글")
                        .member(member)
                        .board(board)
                        .build()
        );

        board.setReplyList(replyList);
        boardRepository.save(board);
        return board;
    }

    @Test
    public void JpaqueryTest()
    {
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "id");
        String keyword = "jpa";
        String type = "w";

        Page<Object[]> result =  customCrudRepository.getCustomPage(type, keyword, pageable);
        System.out.println("" + result);
        System.out.println("TOTAL PAGES : " + result.getTotalPages());
        System.out.println("TOTAL SIZE : " + result.getSize());
        result.getContent().forEach(arr->System.out.println(Arrays.toString(arr)));
    }

    @Test
    public void 멤버_생성_테스트()
    {
        for (int i = 0; i <= 100; i++)
        {
            Member member = new Member();
            member.setId("user" + i);
            String pw = passwordEncoder.encode("pw"+i);
            member.setPassword(pw);
            member.setName("사용자"  + i);

            MemberRole role =new MemberRole();
            if ( i <= 80)
            {
                role.setRoleName(Role.GUEST.getRoleName());
            }
            else if (i <= 90)
            {
                role.setRoleName(Role.MANGER.getRoleName());
            }
            else
            {
                role.setRoleName(Role.ADMIM.getRoleName());
            }

            member.setRoles(Arrays.asList(role));
            memberRepository.save(member);
        }

    }
}
