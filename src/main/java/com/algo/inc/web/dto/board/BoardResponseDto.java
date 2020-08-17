package com.algo.inc.web.dto.board;

import com.algo.inc.domain.board.Board;
import com.algo.inc.web.dto.reply.ReplyResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private Long id;
    private String writer;
    private String title;
    private String content;
    private List<ReplyResponseDto> replyList;

    @Builder
    public BoardResponseDto(Board entity)
    {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
    }

    public static BoardResponseDto createBoardResponse(Board entity)
    {
        return new BoardResponseDto(entity);
    }
}
