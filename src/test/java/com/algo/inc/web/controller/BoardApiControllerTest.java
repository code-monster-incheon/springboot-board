package com.algo.inc.web.controller;

import com.algo.inc.domain.member.Member;
import com.algo.inc.domain.member.Role;
import com.algo.inc.web.dto.board.BoardSaveRequestDto;
import com.algo.inc.web.repository.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class BoardApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

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
    @Transactional
    @WithMockUser(username="TestUser", roles={"MEMBER", "ADMIN"})
    public void 게시글_생성_테스트() throws Exception
    {
        // given
        String content = "스타트 스프링 부트2.0";
        String title = "스프링부트2.0";
        String requestUrl = "/api/board";

        BoardSaveRequestDto dto  = BoardSaveRequestDto.builder()
                .content(content)
                .title(title)
                .build();

        String jsonParam = new ObjectMapper().writeValueAsString(dto);

        // when
        MockHttpServletRequestBuilder req = post(requestUrl)
                .content(jsonParam)
                .contentType(MediaType.APPLICATION_JSON);

        // then
        mockMvc.perform(req)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    @Transactional
    @WithMockUser(username="TestUser", roles={"MEMBER","ADMIN"})
    public void 게시글_생성_후_전체_게시글_조회를_하면_마지막_게시글_조회가_되어야_된다() throws Exception
    {
        //given
        String requestUrl = "/api/board/getBoardList";

        // given
        String content = "스타트 스프링 부트2.0";
        String title = "스프링부트2.0";
        String requestSaveUrl = "/api/board";

        BoardSaveRequestDto dto  = BoardSaveRequestDto.builder()
                .content(content)
                .title(title)
                .build();
        String jsonParam = new ObjectMapper().writeValueAsString(dto);

        MockHttpServletRequestBuilder saveRequest = post(requestSaveUrl)
                .content(jsonParam)
                .contentType(MediaType.APPLICATION_JSON);


        //when
        mockMvc.perform(saveRequest)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());

        MockHttpServletRequestBuilder checkRequest =
                get(requestUrl)
                .contentType(MediaType.APPLICATION_JSON);


        // then
        mockMvc.perform(checkRequest)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].title").value(title))
                .andExpect(jsonPath("$.[0].content").value(content));
    }


}
