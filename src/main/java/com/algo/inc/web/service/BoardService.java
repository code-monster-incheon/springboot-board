package com.algo.inc.web.service;

import com.algo.inc.domain.board.Board;
import com.algo.inc.domain.reply.Reply;
import com.algo.inc.util.page.BoardsPage;
import com.algo.inc.web.dto.board.BoardResponseDto;
import com.algo.inc.web.dto.board.BoardSaveRequestDto;
import com.algo.inc.web.dto.board.BoardUpdateRequestDto;
import com.algo.inc.web.dto.reply.ReplyResponseDto;
import com.algo.inc.web.repository.BoardRepository;
import com.algo.inc.web.repository.MemberRepository;
import com.algo.inc.web.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final ReplyRepository replyRepository;
    private final MemberService memberService;
    // Read All, 게시글 전체를 불러오는 메소드
    public List<BoardResponseDto> getBoardList() {
        return boardRepository
                .findAllBoards()
                .stream()
                .map(BoardResponseDto::createBoardResponse)
                .collect(Collectors.toList());
    }

    // Read, 게시글을 불러오면 당연히 댓글도 같이 불러와야함
    public BoardResponseDto findById(Long id) {

        Board board = boardRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id = "+ id));

        List<Reply> replyList = board.getReplyList();
        // 보드 id에 존재하는 모든 댓글을 가져와서 ReponseDto로 변환
        List<ReplyResponseDto> list = new ArrayList<>();
        for(int i = 0; i < replyList.size(); i++){
            ReplyResponseDto replyResponseDto = new ReplyResponseDto();
            replyResponseDto.setId(replyList.get(i).getId());
            replyResponseDto.setWriter(replyList.get(i).getMember().getId());
            replyResponseDto.setContent(replyList.get(i).getContent());
            replyResponseDto.setReg_dt(replyList.get(i).getRegDt());
            list.add(replyResponseDto);
        }

        BoardResponseDto boardResponseDto = new BoardResponseDto();
        boardResponseDto.setId(board.getId());
        boardResponseDto.setWriter(board.getMember().getId());
        boardResponseDto.setTitle(board.getTitle());
        boardResponseDto.setContent(board.getContent());
        boardResponseDto.setReplyList(list);
        return boardResponseDto;
    }

    // Create
    public Long registerBoard(BoardSaveRequestDto boardSaveRequestDto, UserDetails principal) {
        Board board = new Board();
        board.setMember(memberRepository.findById(principal.getUsername()).get());
        board.setTitle(boardSaveRequestDto.getTitle());
        board.setContent(boardSaveRequestDto.getContent());
        return boardRepository.save(board).getId();
    }

    // Update
    public void updateBaord(Long boardId, BoardUpdateRequestDto boardUpdateRequestDto) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 게시글"));

        board.setTitle(boardUpdateRequestDto.getTitle());
        board.setContent(boardUpdateRequestDto.getContent());
        boardRepository.save(board);
    }

    // Delete
    public void deleteBoardById(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 게시글"));
        boardRepository.delete(board);
    }

    public Page<Board> getAllBoardList()
    {
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "id");
        return boardRepository.findAll(pageable);
    }


    public Board getBoard(Board board) {
        return boardRepository.findById(board.getId()).get();
    }

    public void deleteBoard(Board board) {
        boardRepository.deleteById(board.getId());
    }

    public void insertBoard(Board board) {
        boardRepository.save(board);
    }

    public Page<Board> getViewBoardList(Board board) {
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "id");
        return boardRepository.getAllBoardList(pageable);
    }

    // 유저가 작성한 게시글 모두 불러오기
    public List<BoardResponseDto> getBoardListByMember(String memberId) {
        List<Board> boardList = boardRepository.findAllByMember_IdOrderByIdDesc(memberId);
        List<BoardResponseDto> list = new ArrayList<>();
        for(int i = 0; i < boardList.size();i++){
            BoardResponseDto boardResponseDto = new BoardResponseDto();
            boardResponseDto.setId(boardList.get(i).getId());
            boardResponseDto.setWriter(boardList.get(i).getMember().getId());
            boardResponseDto.setTitle(boardList.get(i).getTitle());
            boardResponseDto.setContent(boardList.get(i).getContent());
            list.add(boardResponseDto);
        }
        return list;
    }

    public Page<Board> getBoardPageList(BoardsPage boardsPage, Pageable page)
    {
        return boardRepository.findAll(
                boardRepository.makePredicate(boardsPage.getType(), boardsPage.getKeyword()), page
        );
    }

    public Board getView(Long id, BoardsPage boardsPage)
    {
        return boardRepository.findById(id).get();
    }

    public void save(Board board) {
        board.setMember(memberService.getMockUser());
        boardRepository.save(board);
    }
}
