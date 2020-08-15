package com.algo.inc.web.controller;

import com.algo.inc.web.dto.board.MemberResponseDto;
import com.algo.inc.web.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member")
public class MemberApiController {

    private final MemberService memberService;
    @GetMapping("/{id}")
    public MemberResponseDto getMember(@PathVariable String id)
    {
        return memberService.getMember(id);
    }
}
