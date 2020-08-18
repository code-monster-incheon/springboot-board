package com.algo.inc.web.dto.reply;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
// 댓글 생성, 댓글 수정 둘다 사용 가능한 Dto
public class ReplySaveRequestDto {
    private String content;
}
