package com.algo.inc.web.controller;

import com.algo.inc.domain.board.Board;
import com.algo.inc.domain.member.Member;
import com.algo.inc.domain.member.MemberRole;
import com.algo.inc.domain.member.Role;
import com.algo.inc.web.dto.board.BoardSaveRequestDto;
import com.algo.inc.web.repository.BoardRepository;
import com.algo.inc.web.repository.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Lists;
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

import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Autowired
    private BoardRepository boardRepository;

    // 게시글 생성 테스트
    @Test
    @Transactional
    @WithMockUser(username="TestUser", roles={"MEMBER", "ADMIN", "MANAGER"})
    public void 게시글_생성_테스트() throws Exception
    {
        // given
        String content = "스타트 스프링 부트2.0";
        String title = "스프링부트2.0";
        String requestUrl = "/api/board";

        BoardSaveRequestDto dto  = new BoardSaveRequestDto();
        dto.setTitle(title);
        dto.setContent(content);

        String jsonParam = new ObjectMapper().writeValueAsString(dto);

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

        BoardSaveRequestDto dto  = new BoardSaveRequestDto();
        dto.setTitle(title);
        dto.setContent(content);
        // 필수작업중 하나
        String jsonParam = new ObjectMapper().writeValueAsString(dto);

        // 게시글 생성 작업
        MockHttpServletRequestBuilder saveRequest = post(requestSaveUrl)
                .content(jsonParam)
                .contentType(MediaType.APPLICATION_JSON);

        //when
        mockMvc.perform(saveRequest)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());


        // /api/board/getBoardList 에서 게시글 역순으로 불러오는 작업
        MockHttpServletRequestBuilder checkRequest = get(requestUrl)
                .contentType(MediaType.APPLICATION_JSON);

        // then
        mockMvc.perform(checkRequest)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].title").value(title))
                .andExpect(jsonPath("$.[0].content").value(content));
    }

    // 수정 테스트
    @Test
    @WithMockUser(username="TestUser", roles={"MEMBER","ADMIN"})
    public void 게시글_수정_테스트() throws Exception
    {
        List<Board> list = boardRepository.findAllBoards();
        Collections.shuffle(list);
        String updateId = Long.toString(list.get(0).getId());

        // given
        String new_content = "게시글이 수정됨.";
        String new_title = "Updated";
        String requestUpdateUrl = "/api/board/" + updateId;

        BoardSaveRequestDto dto  = new BoardSaveRequestDto();
        dto.setTitle(new_title);
        dto.setContent(new_content);
        // 필수작업중 하나
        String jsonParam = new ObjectMapper().writeValueAsString(dto);

        MockHttpServletRequestBuilder saveRequest = put(requestUpdateUrl)
                .content(jsonParam)
                .contentType(MediaType.APPLICATION_JSON);

        //when
        mockMvc.perform(saveRequest)
                .andDo(print())
                .andExpect(status().isOk());
    }

    // 삭제 테스트
    @Test
    @Transactional
    @WithMockUser(username="TestUser", roles={"MEMBER","ADMIN"})
    public void 게시글_번호로_삭제_테스트() throws Exception
    {
        List<Board> list = boardRepository.findAllBoards();
        Collections.shuffle(list);

        String deleteId = Long.toString(list.get(0).getId());
        String requestUrl = "/api/board/" + deleteId;

        MockHttpServletRequestBuilder deleteReq = delete(requestUrl)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(deleteReq)
                .andDo(print())
                .andExpect(status().isOk());
    }

    // 수정 테스트
    @Test
    @Transactional
    public void 게시글_수정_테스트2() throws Exception
    {
        List<Board> list = boardRepository.findAllBoards();
        Collections.shuffle(list);
        String updateId = Long.toString(list.get(0).getId());

        // given
        String new_content = "게시글이 수정됨.";
        String new_title = "Updated";
        String requestUpdateUrl = "/api/board/" + updateId;

        BoardSaveRequestDto dto  = new BoardSaveRequestDto();
        dto.setTitle(new_title);
        dto.setContent(new_content);
        // 필수작업중 하나
        String jsonParam = new ObjectMapper().writeValueAsString(dto);

        MockHttpServletRequestBuilder saveRequest = put(requestUpdateUrl)
                .content(jsonParam)
                .contentType(MediaType.APPLICATION_JSON);

        //when
        mockMvc.perform(saveRequest)
                .andDo(print())
                .andExpect(status().isOk());

        // 수정 확인 작업
        MockHttpServletRequestBuilder checkRequest = get("/api/board/" + updateId)
                .contentType(MediaType.APPLICATION_JSON);

        //when
        mockMvc.perform(checkRequest)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(new_title))
                .andExpect(jsonPath("$.content").value(new_content));

    }

    @Test
    @WithMockUser(username="TestUser", roles={"MEMBER","ADMIN"})
    public void 게시글_상세조회_테스트() throws Exception
    {
        List<Board> boardList = boardRepository.findAllBoards();
        for(int i = 0; i < boardList.size(); i++){
            String id = Long.toString(boardList.get(i).getId());
            String requestUrl = "/api/board/" + id;

            MockHttpServletRequestBuilder readRequest = get(requestUrl)
                    .contentType(MediaType.APPLICATION_JSON);

            mockMvc.perform(readRequest)
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(id));
        }
    }

    @Test
    public void 유저_아이디로_게시글_조회_테스트() throws Exception
    {
        String id = "spring";
        String requestUrl = "/api/board/getBoard?memberId=" + id;
        MockHttpServletRequestBuilder readRequest = get(requestUrl)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(readRequest)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].writer").value(id));
    }
}
