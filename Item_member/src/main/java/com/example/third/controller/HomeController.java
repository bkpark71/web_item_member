package com.example.third.controller;

import com.example.third.domain.Member;
import com.example.third.service.MemberService;
import com.example.third.session.CookieConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HomeController {
    MemberService memberService;
    @Autowired
    public HomeController(MemberService memberService) {
        this.memberService = memberService;
    }
    @GetMapping("/")
    public String home(
            @CookieValue(value = "MemberId", required=false) Long memberId, Model model) {
        System.out.println("memberId : " + memberId);
        log.info("member id : [{}] ", memberId);
        if(memberId == null) { // 로그인 안한 사용자 , // 로그인 하지 않은 사람은 home 으로 화면 보여져서 로그인 하도록
            return "home";
        } else { // 로그인 한 사람은 상픔관리 목록으로 이동하거나, 로그아웃 하도록
            Member member = memberService.findMemberById(memberId).get();
            model.addAttribute("member", member);
            return "loginHome";
        }
    }
}
