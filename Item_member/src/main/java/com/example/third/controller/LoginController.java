package com.example.third.controller;

import com.example.third.domain.Member;
import com.example.third.service.MemberService;
import com.example.third.session.CookieConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
public class LoginController {
    MemberService memberService;
    @Autowired
    public LoginController(MemberService memberService) {
        this.memberService = memberService;
    }
    @GetMapping("/login")
    public String memberLogin(Model model){
        Member member = new Member();
        model.addAttribute("member",member);
        return "login";
    }
    @PostMapping("/login")
    public String memberLogin(@ModelAttribute Member member, HttpServletResponse response){
        String loginId = member.getLoginId();
        Optional<Member> member1 = memberService.findMember(loginId);// member 가 존재해야 함. member.password == 화면에서 입력한 password 랑 일치해야 함

        // false && false ==> false  두번째 조건식은 pass
        // false && true ==> false   두번째 조건식은 pass
        // true && false ==> false   두번째 조건식에 따라 true이거나 false가 되기 때문에 두번째 조건식을 반드시 조사함.
        // true && true ==> true     두번째 조건식에 따라 true이거나 false가 되기 때문에 두번째 조건식을 반드시 조사함.
        // 문제 상황 -- > member1 empty 인 경우 [ ] 빈 리스트, NoSuchElementException 발생
        if(!member1.isEmpty() && member1.get().getPassword().equals(member.getPassword())){
            Cookie memberId = new Cookie("MemberId", String.valueOf(member1.get().getId()));
            response.addCookie(memberId);

            return "redirect:/basic/items";
        }
        return "redirect:/login";
    }

}
