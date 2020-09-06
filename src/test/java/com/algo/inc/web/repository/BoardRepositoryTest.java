package com.algo.inc.web.repository;

import com.algo.inc.domain.board.Board;
import com.algo.inc.domain.member.Member;
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
    // 테스트코드가 실행되기 전에 실행되는 코드
    @Before
    public void initTestMember()
    {
        Member member = new Member();
        member.setId("TestUser");
        member.setEnabled(true);
        member.setRole(Role.ROLE_MEMBER);
        member.setEmail("test@test.com");
        member.setName("TestUser");
        memberRepository.save(member);
    }

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
        //TODO : 댓글도 같이 생성시키기
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
    public void JpaquertTest()
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


}
