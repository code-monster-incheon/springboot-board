package com.algo.inc.web.dto.board;

import com.algo.inc.domain.board.Board;
import com.algo.inc.web.dto.reply.ReplyResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class BoardResponseDto {
    private Long id;
    private String writer;
    private String title;
    private String content;
    private List<ReplyResponseDto> replyList;

}
