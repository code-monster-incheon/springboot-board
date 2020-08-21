package com.algo.inc.web.controller;

import com.algo.inc.domain.board.Board;
import com.algo.inc.domain.member.Member;
import com.algo.inc.domain.member.Role;
import com.algo.inc.web.dto.board.BoardSaveRequestDto;
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

    // 게시글 생성 테스트
    @Test
    @Transactional      // 생성 테스트 이후, 생성한 튜플을 즉시 삭제함
    @WithMockUser(username="TestUser", roles={"MEMBER", "ADMIN"})
    public void 게시글_생성_테스트() throws Exception
    {
        // given
        String content = "스타트 스프링 부트2.0";
        String title = "스프링부트2.0";
        String requestUrl = "/api/board";

        BoardSaveRequestDto dto  = new BoardSaveRequestDto();
        dto.setTitle(title);
        dto.setContent(content);

        // 필수작업 중 하나
        String jsonParam = new ObjectMapper().writeValueAsString(dto);

        // when, api/board 에서 postmapping 어노테이션으로 jsonParam을 보낸다
        MockHttpServletRequestBuilder req = post(requestUrl)
                .content(jsonParam)
                .contentType(MediaType.APPLICATION_JSON);

        // then, 이후 게시글이 생성됨
        mockMvc.perform(req)
                .andDo(print())             // test 응답 결과 출력
                .andExpect(status().isOk())     // 요청결과에 따라 결과 status가 200인지 검증
                .andExpect(jsonPath("$").isNotEmpty());
                // 게시글을 생성하면 id를 반환하는데, 반환값이 empty가 아닌지 검증
    }

    @Test
    @Transactional
    @WithMockUser(username="TestUser", roles={"MEMBER","ADMIN"})
    public void 게시글_생성_후_전체_게시글_조회를_하면_마지막_게시글_조회가_되어야_된다() throws Exception {
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
    public void 게시글_수정_테스트() throws Exception{
        // 게시글 수정을 할 수 있다는 건,
        // 시큐리티 검사로 인해 현재 로그인된 유저 정보와 게시글의 작성유저 정보가 일치하다는 뜻
        // 그렇기 때문에 수정하기 버튼이 존재한다는 것으로 가정

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
    public void 게시글_번호로_삭제_테스트() throws Exception{
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
    public void 게시글_수정_테스트2() throws Exception{
        // 게시글 수정을 할 수 있다는 건,
        // 시큐리티 검사로 인해 현재 로그인된 유저 정보와 게시글의 작성유저 정보가 일치하다는 뜻
        // 그렇기 때문에 수정하기 버튼이 존재한다는 것으로 가정

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
    public void 게시글_상세조회_테스트() throws Exception {
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
    public void 유저_아이디로_게시글_조회_테스트() throws Exception{
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
