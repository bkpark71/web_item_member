package com.example.third.controller;

import com.example.third.domain.Member;
import com.example.third.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/members")
public class MemberController {

    MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    @GetMapping("/join")
    public String memberJoin(Model model){
        Member member = new Member();
        model.addAttribute("member",member);
        return "members/join";
    }
    @GetMapping("/login")
    public String memberLogin(Model model){
        Member member = new Member();
        model.addAttribute("member",member);
        return "members/login";
    }
    @PostMapping("/join")
    public String join(@ModelAttribute Member member){
        System.out.println("member = " + member);
        Long id = memberService.join(member);
        System.out.println(id+" 번 회원 가입 완료");
        return "redirect:/members/login";
    }
    @PostMapping("/login")
    public String memberLogin(@ModelAttribute Member member){
        String loginId = member.getLoginId();
        Optional<Member> member1 = memberService.findMember(loginId);// member 가 존재해야 함. member.password == 화면에서 입력한 password 랑 일치해야 함

        // false && false ==> false  두번째 조건식은 pass
        // false && true ==> false   두번째 조건식은 pass
        // true && false ==> false   두번째 조건식에 따라 true이거나 false가 되기 때문에 두번째 조건식을 반드시 조사함.
        // true && true ==> true     두번째 조건식에 따라 true이거나 false가 되기 때문에 두번째 조건식을 반드시 조사함.

         // 문제 상황 -- > member1 empty 인 경우 [ ] 빈 리스트, NoSuchElementException 발생
        if(!member1.isEmpty() && member1.get().getPassword().equals(member.getPassword())){
            return "redirect:/basic/items";
        }
        return "redirect:/members/login";
    }
}
