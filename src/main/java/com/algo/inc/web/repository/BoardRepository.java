package com.algo.inc.web.repository;


import com.algo.inc.domain.board.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("SELECT b FROM Board b")
    Page<Board> getBoardList(Pageable pageble);
}
