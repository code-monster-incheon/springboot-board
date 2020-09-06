package com.algo.inc.web.repository;


import com.algo.inc.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomCrudRepository extends JpaRepository<Board, Long>, CustomWebBoard{
}
