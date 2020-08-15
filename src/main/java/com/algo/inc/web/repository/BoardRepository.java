package com.algo.inc.web.repository;


import com.algo.inc.domain.board.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>, QuerydslPredicateExecutor<Board> {

    @Query("SELECT b FROM Board b")
    Page<Board> getAllBoardList(Pageable pageble);

    @Query("SELECT b FROM Board b ORDER BY b.id DESC ")
    List<Board> findAllBoards();
}
