package com.algo.inc.domain.member;

import lombok.Getter;

@Getter
public enum Role {
    ADMIM("ADMIN", "관리자"),
    GUEST("GUEST", "일반 접속 고객"),
    MANGER("MANGER", "매니저");

    private String roleName;
    private String acc;

    Role(String roleName, String acc)
    {
        this.roleName = roleName;
        this.acc = acc;
    }
}
