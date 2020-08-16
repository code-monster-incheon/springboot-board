package com.algo.inc.web.dto.board;

import com.algo.inc.domain.board.Board;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardResponseDto {
    private Long id;
    private String title;
    private String content;

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
