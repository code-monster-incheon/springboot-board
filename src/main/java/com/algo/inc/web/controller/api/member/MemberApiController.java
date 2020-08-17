package com.algo.inc.web.controller.api.member;

import com.algo.inc.web.dto.member.MemberLoginRequestDto;
import com.algo.inc.web.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/system/login")
public class MemberApiController {

    private final MemberService memberService;


    @PostMapping
    public boolean memberLogin(@RequestBody MemberLoginRequestDto memberLoginRequestDto){
        return memberService.memberLogin(memberLoginRequestDto);
    }

}
