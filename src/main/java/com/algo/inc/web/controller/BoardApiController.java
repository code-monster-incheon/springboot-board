package com.algo.inc.web.controller;

import com.algo.inc.domain.board.Board;
import com.algo.inc.web.dto.board.BoardResponseDto;
import com.algo.inc.web.dto.board.BoardSaveRequestDto;
import com.algo.inc.web.dto.board.BoardUpdateRequestDto;
import com.algo.inc.web.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/board")
public class BoardApiController {

    private final BoardService boardService;


    @GetMapping("/{id}")
    public BoardResponseDto findById(@PathVariable Long id)
    {
        return boardService.findById(id);
    }

    @PostMapping
    public void registerBoard(@RequestBody BoardSaveRequestDto boardSaveRequestDto)
    {
        boardService.registerBoard(boardSaveRequestDto);
    }

    @PutMapping()
    public void updateBoard(@RequestBody BoardUpdateRequestDto boardUpdateRequestDto)
    {
        boardService.updateBaord(boardUpdateRequestDto);
    }

    @GetMapping("/getBoardList")
    public String getBoardList(Model model)
    {
        Page<Board> boardList = boardService.getBoardList();
        model.addAttribute("boardList", boardList);
        return "board/getBoardList";
    }
}
