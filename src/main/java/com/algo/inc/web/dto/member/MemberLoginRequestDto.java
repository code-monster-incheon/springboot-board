package com.algo.inc.web.dto.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class MemberLoginRequestDto {
    private String id;
    private String password;
}
