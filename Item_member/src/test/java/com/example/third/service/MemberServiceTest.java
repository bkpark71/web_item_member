package com.example.third.service;

import com.example.third.domain.Member;
import com.example.third.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    MemberService memberService;
    MemberRepository memberRepository;

    @Autowired
    public MemberServiceTest(MemberService memberService, MemberRepository memberRepository) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }
    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("홍길동");
        member.setLoginId("aaa");
        member.setPassword("1111!");
        //when
        Long join = memberService.join(member);
        Member findMember = memberService.findMemberById(join).get();
        //then
        System.out.println("findMember = " + findMember);
        System.out.println("mmber = " + member);
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}