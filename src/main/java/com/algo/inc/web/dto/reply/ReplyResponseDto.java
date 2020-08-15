package com.algo.inc.web.dto.reply;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class ReplyResponseDto {
    private Long id;
    private String writer;
    private String content;
    private LocalDateTime reg_dt;
}
