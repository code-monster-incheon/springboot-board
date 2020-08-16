package com.algo.inc.web.service;

import com.algo.inc.domain.board.Board;
import com.algo.inc.web.dto.board.BoardResponseDto;
import com.algo.inc.web.dto.board.BoardSaveRequestDto;
import com.algo.inc.web.dto.board.BoardUpdateRequestDto;
import com.algo.inc.web.repository.BoardRepository;
import com.algo.inc.web.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    public BoardResponseDto findById(Long id)
    {
        Board entity = boardRepository
                .findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다. id = "+ id));

        return new BoardResponseDto(entity);
    }

    public Long registerBoard(BoardSaveRequestDto boardSaveRequestDto, UserDetails principal) {

        Board board = new Board();
        board.setMember(memberRepository.findById(principal.getUsername()).get());
        board.setTitle(boardSaveRequestDto.getTitle());
        board.setContent(boardSaveRequestDto.getContent());
        return boardRepository.save(board).getId();
    }

    public void updateBaord(BoardUpdateRequestDto boardUpdateRequestDto)
    {
        Optional<Board> board = boardRepository.findById(boardUpdateRequestDto.getId());

        board.ifPresent(b->
            boardRepository.save(Board.builder()
                    .content(b.getContent())
                    .title(b.getTitle())
                    .build()
            )
        );

    }
    public Page<Board> getAllBoardList()
    {
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "id");
        return boardRepository.findAll(pageable);
    }

    public List<BoardResponseDto> getBoardList()
    {
        return boardRepository
                .findAllBoards()
                .stream()
                .map(BoardResponseDto::createBoardResponse)
                .collect(Collectors.toList());
    }

    // TODO : ajax 로 바꾼후에 수정 할 것
//    public Page<Board> getBoardList2(Search search) {
//        BooleanBuilder builder = new BooleanBuilder();
//        QBoard qBoard = QBoard.board;
//        if (search.getSearchCondition().equals("TITLE"))
//        {
//            builder.and(qBoard.title.like("%" + search.getSearchKeyword() + "%"));
//        }
//        else if(search.getSearchCondition().equals("CONTENT"))
//
//        Pageable pageable = PageRequest.of(0, 10, Sort.Direct{
//            builder.and(qBoard.content.like("%" + search.getSearchKeyword() + "%"));
//        }
//        ion.DESC, "id");
//        return boardRepository.findAll(builder, pageable);
//    }

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
}
