package com.algo.inc.web.repository;

import com.algo.inc.domain.board.Board;
import com.algo.inc.domain.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, String> {
    List<Member> findByAccount(String account);
    @Query("SELECT b FROM Board b")
    Page<Board> getBoardList(Pageable pageble);
}
