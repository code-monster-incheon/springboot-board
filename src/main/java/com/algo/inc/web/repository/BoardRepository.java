package com.algo.inc.web.repository;


import com.algo.inc.domain.board.Board;
import com.algo.inc.domain.board.QBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
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

    List<Board> findAllByMember_IdOrderByIdDesc(String memberId);
    List<Board> findByMember(String memberId);

    Page<Board> findByIdGreaterThan(Long id, Pageable page);

    public default Predicate makePredicate(String type, String keyword)
    {
        BooleanBuilder builder = new BooleanBuilder();
        QBoard board = QBoard.board;

        builder.and(board.id.gt(0));

        if (type == null)
            return builder;

        switch (type){
            case "t":
                builder.and(board.title.like("%" + keyword + "%"));
                break;
            case "c":
                builder.and(board.content.like("%" + keyword + "%"));
                break;
            case "w":
                builder.and(board.member.id.like("%" + keyword + "%"));
                break;
        }
        return builder;
    }


}
