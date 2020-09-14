package com.algo.inc.config.security;

import com.algo.inc.domain.member.Member;
import com.algo.inc.domain.member.MemberRole;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CustomUserSecurityUser extends User {

    private static final String ROLE_PREFIX = "ROLE_";

    private Member member;

    public CustomUserSecurityUser(Member member)
    {
        super(member.getId(), member.getPassword(), makeGrantedAuthority(member.getRoles()));
        this.member = member;
    }

    private static List<GrantedAuthority> makeGrantedAuthority(List<MemberRole> roles)
    {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(ROLE_PREFIX + role.getRoleName()))
                .collect(Collectors.toList());
    }
}
