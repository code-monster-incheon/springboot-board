package com.algo.inc.web.controller.api.board;

import com.algo.inc.web.dto.board.BoardResponseDto;
import com.algo.inc.web.dto.board.BoardSaveRequestDto;
import com.algo.inc.web.dto.board.BoardUpdateRequestDto;
import com.algo.inc.web.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
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
    public Long registerBoard(@RequestBody BoardSaveRequestDto boardSaveRequestDto, Authentication authentication)
    {
        return boardService.registerBoard(boardSaveRequestDto, (UserDetails) authentication.getPrincipal());
    }

    @PutMapping
    public void updateBoard(@RequestBody BoardUpdateRequestDto boardUpdateRequestDto)
    {
        boardService.updateBaord(boardUpdateRequestDto);
    }

    @GetMapping("/getBoardList")
    public List<BoardResponseDto> getBoardList()
    {
        return boardService.getBoardList();
    }

}
