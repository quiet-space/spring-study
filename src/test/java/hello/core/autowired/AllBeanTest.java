//package hello.core.autowired;
//
//import hello.core.AutoAppConfig;
//import hello.core.discount.DiscountPolicy;
//import hello.core.member.Grade;
//import hello.core.member.Member;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//import java.util.List;
//import java.util.Map;
//
//import static org.assertj.core.api.Assertions.*;
//
//public class AllBeanTest {
//    @Test
//    void findAllBean() {
//        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
//        DiscountService discountService = ac.getBean(DiscountService.class);
//
//        Member member = new Member(1L, "userA", Grade.VIP);
//        // int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");
//        // fixDiscountPolicy 가 안 되는 이유 => discountService.discount의 3번째 인자는 DiscountPolicy 타입이어야 하는데
//        // rate는 해당 타입이 맞지만, fix의 경우는 아니기 때문
//
//        int discountPrice = discountService.discount(member, 10000, "rateDiscountPolicy");
//
//        assertThat(discountService).isInstanceOf(DiscountService.class);
//        assertThat(discountPrice).isEqualTo(1000);
//    }
//
//    static class DiscountService {
//        private final Map<String, DiscountPolicy> policyMap;
//        private final List<DiscountPolicy> policies;
//
//        @Autowired // 생성자 -> 파라미터에 필요한 모든 class를 자동 주입
//        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
//            this.policyMap = policyMap;
//            this.policies = policies;
//
////            System.out.println("policyMap = " + policyMap);
////            System.out.println("policies = " + policies);
//        }
//
//        public int discount(Member member, int price, String discountCode) {
//            DiscountPolicy discountPolicy = policyMap.get(discountCode);
//
//            System.out.println("discountCode = " + discountCode);
//            System.out.println("discountPolicy = " + discountPolicy);
//
//            return discountPolicy.discount(member, price);
//        }
//    }
//}
