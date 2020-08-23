package com.algo.inc.web.controller.view.board;

import com.algo.inc.config.security.SecurityUser;
import com.algo.inc.domain.board.Board;
import com.algo.inc.util.page.BoardsPage;
import com.algo.inc.util.page.PageMaker;
import com.algo.inc.web.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/view/board")
public class BoardViewController {

    private final BoardService boardService;

    @GetMapping("/list")
    public String list(@ModelAttribute("boardsPage") BoardsPage boardsPage, Model model)
    {
        Pageable page = boardsPage.makePageable(0, "id");
        Page<Board> result = boardService.getBoardPageList(page);
        log.info("page 출력 : " + page);
        log.info("result 출력 " + result);

        model.addAttribute("result", new PageMaker(result));
        return "board/list";
    }

    @GetMapping("/getBoardList")
    public String getBoardList(Model model, Board board)
    {
        Page<Board> boardList = boardService.getViewBoardList(board);
        model.addAttribute("boardList", boardList);
        return "board/getBoardList";
    }

    @GetMapping("/getBoard")
    public String getBoard(Board board, Model model)
    {
        log.debug(board.toString());
        model.addAttribute("board", boardService.getBoard(board));
        return "board/getBoard";
    }

    @GetMapping("/deleteBoard")
    public String deleteBoard(Board board)
    {
        boardService.deleteBoard(board);
        return "redirect:getBoardList";
    }

    @GetMapping("/insertBoard")
    public String insertBoardView()
    {
        return "/board/insertBoard";
    }

    @PostMapping("/insertBoard")
    public String insertBoard(Board board, @AuthenticationPrincipal SecurityUser principal)
    {
        board.setMember(principal.getMember());
        boardService.insertBoard(board);
        return "redirect:getBoardList";
    }

}
