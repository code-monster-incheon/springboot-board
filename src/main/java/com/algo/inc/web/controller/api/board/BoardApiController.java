package com.algo.inc.web.controller.api.board;

import com.algo.inc.web.dto.board.BoardResponseDto;
import com.algo.inc.web.dto.board.BoardSaveRequestDto;
import com.algo.inc.web.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/board")
public class BoardApiController {

    private final BoardService boardService;

    @GetMapping("/getBoardList")
    public List<BoardResponseDto> getBoardList()
    {
        return boardService.getBoardList();
    }

    @GetMapping("/getBoard")
    public List<BoardResponseDto> getBoardListByMember(@RequestParam("memberId") String memberId)
    {
        return boardService.getBoardListByMember(memberId);
    }

    @PostMapping
    public ResponseEntity<Void> registerBoard(@RequestBody BoardSaveRequestDto boardSaveRequestDto)
    {
        boardService.registerBoard(boardSaveRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteBoardById(@PathVariable Long id)
   {
        boardService.deleteBoardById(id);
   }
}
