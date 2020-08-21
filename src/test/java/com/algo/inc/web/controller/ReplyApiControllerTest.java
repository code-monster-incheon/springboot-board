package com.algo.inc.web.controller;

import com.algo.inc.domain.board.Board;
import com.algo.inc.domain.member.Member;
import com.algo.inc.domain.member.Role;
import com.algo.inc.domain.reply.Reply;
import com.algo.inc.web.dto.reply.ReplySaveRequestDto;
import com.algo.inc.web.repository.BoardRepository;
import com.algo.inc.web.repository.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.parameters.P;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.transaction.Transactional;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class ReplyApiControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BoardRepository boardRepository;

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
    //@Transactional
    @WithMockUser(username="TestUser", roles={"MEMBER", "ADMIN"})
    public void 댓글_생성_테스트() throws Exception{
        List<Board> boardList = boardRepository.findAll();

        String saveReqUrl = "/api/reply/1";
        String content = "TestUser가 1번 게시글에 댓글을 달았습니다.";
        ReplySaveRequestDto replySaveRequestDto = new ReplySaveRequestDto();
        replySaveRequestDto.setContent(content);

        String jsonParam = new ObjectMapper().writeValueAsString(replySaveRequestDto);

        MockHttpServletRequestBuilder saveReq = post(saveReqUrl)
                .content(jsonParam)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(saveReq)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }
}
