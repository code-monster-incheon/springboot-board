package com.algo.inc.web.controller.view.board;

import com.algo.inc.domain.board.Board;
import com.algo.inc.domain.member.Role;
import com.algo.inc.util.page.BoardsPage;
import com.algo.inc.util.page.PageMaker;
import com.algo.inc.web.repository.BoardRepository;
import com.algo.inc.web.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/view/board")
public class BoardViewController {

    private final BoardService boardService;
    private final BoardRepository boardRepository;

    @GetMapping("/list")
    public String list(@ModelAttribute("boardsPage") BoardsPage boardsPage, Model model)
    {
        Pageable page = boardsPage.makePageable(0, "id");
        Page<Object[]> result = boardService.getBoardPageList(boardsPage, page);
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

    @GetMapping("/register")
    public String registerGET(@ModelAttribute("board") Board board)
    {
        return "board/register";
    }

    @GetMapping("/detail")
    public String view(Long id, @ModelAttribute("boardsPage") BoardsPage boardsPage, Model model)
    {
        log.debug("size : ", boardsPage.getSize());
        log.debug("page : " + boardsPage.getPage());

        Board board = boardService.getView(id, boardsPage);
        model.addAttribute("vo", board);
        return "board/detail";
    }

    @Secured(value = {"ROLE_GUEST", "ROLE_MANAGER", "ROLE_ADMIN"})
    @PostMapping("/modify")
    public String modifyPost(Board board, BoardsPage boardsPage, RedirectAttributes rttr)
    {

        boardRepository.findById(board.getId()).ifPresent(origin->
        {
            origin.setTitle(board.getTitle());
            origin.setContent(board.getContent());
            boardRepository.save(origin);
            rttr.addFlashAttribute("msg", "success");
            rttr.addAttribute("id", origin.getId());
        });

        rttr.addAttribute("page", boardsPage.getPage());
        rttr.addAttribute("size", boardsPage.getSize());
        rttr.addAttribute("type", boardsPage.getType());
        rttr.addAttribute("keyword", boardsPage.getKeyword());

        return "redirect:/view/board/detail";
    }

    @Secured(value = {"ROLE_GUEST", "ROLE_MANAGER", "ROLE_ADMIN"})
    @GetMapping("/modify")
    public String modify(Long id, @ModelAttribute("boardsPage") BoardsPage boardsPage, Model model)
    {
        System.out.println("!!!!!!");
        log.debug("size : ", boardsPage.getSize());
        log.debug("page : ", boardsPage.getPage());

        boardRepository.findById(id).ifPresent(board->model.addAttribute("board", board));
        return "board/modify";
    }

    @Secured(value = {"ROLE_GUEST", "ROLE_MANAGER", "ROLE_ADMIN"})
    @PostMapping("/delete")
    public String delete(Long id, BoardsPage boardsPage, RedirectAttributes rttr)
    {
        boardRepository.deleteById(id);
        rttr.addFlashAttribute("msg", "success");
        rttr.addAttribute("page", boardsPage.getPage());
        rttr.addAttribute("size", boardsPage.getSize());
        rttr.addAttribute("type", boardsPage.getType());
        rttr.addAttribute("keyword", boardsPage.getKeyword());

        return "redirect:/view/board/list";
    }

}
