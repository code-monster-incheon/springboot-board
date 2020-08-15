package com.algo.inc.web.service;

import com.algo.inc.domain.board.Board;
import com.algo.inc.web.dto.board.BoardResponseDto;
import com.algo.inc.web.dto.board.BoardSaveRequestDto;
import com.algo.inc.web.dto.board.BoardUpdateRequestDto;
import com.algo.inc.web.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardResponseDto findById(Long id)
    {
        Board entity = boardRepository
                .findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다. id = "+ id));

        return new BoardResponseDto(entity);
    }

    public void registerBoard(BoardSaveRequestDto boardSaveRequestDto) {
        Board board = new Board();
        board.setTitle(boardSaveRequestDto.getTitle());
        board.setContent(boardSaveRequestDto.getContent());
        board.setView(boardSaveRequestDto.getView());
        boardRepository.save(board);
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

    public Page<Board> getBoardList() {
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "id");
        return boardRepository.getBoardList(pageable);
    }

    public Board getBoard(Board board) {
        return boardRepository.findById(board.getId()).get();
    }
}
