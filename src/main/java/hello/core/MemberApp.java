package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService(); // DIP 충족 (추상, 구현, 주입을 모두 분리)
        // MemberService memberService = new MemberServiceImpl(); // DIP 위반
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName()); // memberA
        System.out.println("find member = " + findMember.getName()); // memberA
    }
}
