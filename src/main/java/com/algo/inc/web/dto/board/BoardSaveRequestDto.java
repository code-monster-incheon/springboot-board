package com.algo.inc.web.dto.board;

import com.algo.inc.domain.board.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BoardSaveRequestDto {

    private String title;
    private String content;

    public Board toEntity()
    {
        return Board.builder().title(title).content(content).build();
    }

}
