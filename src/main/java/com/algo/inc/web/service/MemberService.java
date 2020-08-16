package com.algo.inc.web.service;

import com.algo.inc.domain.member.Member;
import com.algo.inc.web.dto.board.MemberResponseDto;
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

}
