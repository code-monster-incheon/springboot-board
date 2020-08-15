package com.algo.inc.web.repository;

import com.algo.inc.domain.reply.Reply;
import com.algo.inc.web.dto.reply.ReplyResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findAllByBoard_Id(Long id);
}
