package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor // final 필드들로 생성자 만들어줌
public class OrderServiceImpl implements OrderService {
    // 인터페이스에만 의존 (구체 객체 참조X) -> DIP 충족
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // @Autowired // 생성자가 단 1개면 Autowired 어노테이션 생략 가능
    // @RequiredArgsConstructor // Lombok이 생성자 자동 생성 -> 아래 3줄 코드 (사용자 작성 생성자) 생략 필 -> Autowired 생략 가능
    //    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
    //        this.memberRepository = memberRepository;
    //        this.discountPolicy = discountPolicy;
    //    }


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);

    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
