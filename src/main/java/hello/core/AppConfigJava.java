package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfigJava {
    public MemberService memberService() {
        // 생성자 MemberServiceImpl
        // 어떤 구현 객체를 new MemoryMemberRepository() -> 구체적인 인스턴스
        // 추상 인터페이스 MemberService 에
        // 주입=연결 : AppConfig
        return new MemberServiceImpl(memberRepository());
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository(); // 구체 객체가 바뀐다면 해당 코드만 변경하면 됨
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
        //  return new FixDiscountPolicy();
        return new RateDiscountPolicy(); // 구성 영역 = AppConfig 만 변경해도 요구사항 변동에 대응할 수 있다.
    }
}
