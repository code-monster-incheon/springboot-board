package com.algo.inc.web.service;

import com.algo.inc.domain.member.Member;
import com.algo.inc.web.dto.member.MemberLoginRequestDto;
import com.algo.inc.web.dto.member.MemberResponseDto;
import com.algo.inc.web.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberrepository;

    public MemberResponseDto getMember(String id)
    {
        Member member = memberrepository.findById(id).get();

        MemberResponseDto dto = new MemberResponseDto();
        dto.setId(member.getId());
        dto.setName(member.getName());
        return dto;
    }

    // 로그인 처리
    public boolean memberLogin(MemberLoginRequestDto memberLoginRequestDto) {
        Member member = memberrepository.findById(memberLoginRequestDto.getId())
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 회원입니다."));

        // 아이디를 찾았는데, 비밀번호가 틀렸을 때
        if(member.getPassword().equals(memberLoginRequestDto.getPassword()) == false)
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");

        return true;
    }
}
