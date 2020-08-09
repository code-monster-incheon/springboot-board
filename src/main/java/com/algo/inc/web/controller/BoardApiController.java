package com.algo.inc.web.controller;

import com.algo.inc.web.dto.board.BoardResponseDto;
import com.algo.inc.web.dto.board.BoardSaveRequestDto;
import com.algo.inc.web.dto.board.BoardUpdateRequestDto;
import com.algo.inc.web.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/board")
public class BoardApiController {

    private final BoardService boardService;


    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody BoardUpdateRequestDto updateRequestDto)
    {
        return boardService.update(id, updateRequestDto);
    }

    @PostMapping
    public void registerBoard(@RequestBody BoardSaveRequestDto boardSaveRequestDto)
    {
        boardService.registerBoard(boardSaveRequestDto);
    }
    @GetMapping("/{id}")
    public BoardResponseDto findById(@PathVariable Long id)
    {
        return boardService.findById(id);
    }
}
