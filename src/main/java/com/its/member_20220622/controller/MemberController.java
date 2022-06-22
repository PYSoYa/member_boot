package com.its.member_20220622.controller;

import com.its.member_20220622.dto.MemberDTO;
import com.its.member_20220622.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/save-form")
    public String saveForm(){
        return "/memberPages/save";
    }
    @PostMapping("/save")
    public String save(MemberDTO memberDTO){
        memberService.save(memberDTO);
        return "/memberPages/login";
    }
    @GetMapping("/login-form")
    public String loginForm(){
        return "/memberPages/login";
    }
    @PostMapping("/login")
    public String login(@RequestParam("memberEmail") String memberEmail,
                        @RequestParam("memberPassword") String memberPassword){
        memberService.login(memberEmail, memberPassword);

        return "/memberPages/main";
    }
}
