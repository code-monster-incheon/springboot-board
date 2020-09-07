package com.algo.inc.web.dto.reply;

import lombok.Builder;
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

    @Builder
    public ReplyResponseDto(Long id, String writer, String content, LocalDateTime reg_dt) {
        this.id = id;
        this.writer = writer;
        this.content = content;
        this.reg_dt = reg_dt;
    }
}
