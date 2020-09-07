package com.algo.inc.web.controller.api.board;

import com.algo.inc.web.dto.board.BoardResponseDto;
import com.algo.inc.web.dto.board.BoardSaveRequestDto;
import com.algo.inc.web.dto.board.BoardUpdateRequestDto;
import com.algo.inc.web.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/board")
public class BoardApiController {

    private final BoardService boardService;

    // Read All, 게시글 전체 불러오기
    @GetMapping("/getBoardList")
    public List<BoardResponseDto> getBoardList() {
        return boardService.getBoardList();
    }

    // Read by memberId, 유저가 작성한 게시글 모두 불러오기
    @GetMapping("/getBoard")
    public List<BoardResponseDto> getBoardListByMember(@RequestParam("memberId") String memberId)
    {
        return boardService.getBoardListByMember(memberId);
    }

    @PostMapping
    public void registerBoard(@RequestBody BoardSaveRequestDto boardSaveRequestDto)
    {
        log.debug("register Board.....");
        boardService.save(boardSaveRequestDto.toEntity());
    }

    // Update, 게시글 수정, 세션 필요 없음
    @PutMapping("/{id}")
    public void updateBoard(@PathVariable Long id, @RequestBody BoardUpdateRequestDto boardUpdateRequestDto)
    {
        boardService.updateBaord(id, boardUpdateRequestDto);
    }

    // Delete, 게시글 삭제, 댓글도 같이 삭제되는거 확인완료
   @DeleteMapping("/{id}")
    public void deleteBoardById(@PathVariable Long id)
   {
        boardService.deleteBoardById(id);
   }
}
