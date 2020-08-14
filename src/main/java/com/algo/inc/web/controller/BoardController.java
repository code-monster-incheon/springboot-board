package com.algo.inc.web.controller;

import com.algo.inc.domain.board.Board;
import com.algo.inc.web.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board/")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/getBoardList")
    public String getBoardList(Model model)
    {
        Page<Board> boardList = boardService.getBoardList();
        model.addAttribute("boardList", boardList);
        return "board/getBoardList";
    }

    @GetMapping("/getBoard")
    public String getBoard(Board board, Model model)
    {
        model.addAttribute("board", boardService.getBoard(board));
        return "board/getBoard";
    }
}
