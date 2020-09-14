package com.algo.inc.web.dto.reply;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ReplySaveRequestDto {
    private String content;
    private String userId;
}
