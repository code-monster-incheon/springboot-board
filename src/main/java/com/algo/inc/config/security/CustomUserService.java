package com.algo.inc.config.security;


import com.algo.inc.web.repository.MemberRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@Log
public class CustomUserService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Autowired
    public CustomUserService(MemberRepository memberRepository)
    {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findById(username)
                .filter(s-> s != null)
                .map(CustomUserSecurityUser::new).get();
    }
}
