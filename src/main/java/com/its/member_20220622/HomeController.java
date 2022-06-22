package com.its.member_20220622;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class HomeController {
    @GetMapping("/")
    public String index(){
        return "/memberPages/index";
    }
}
