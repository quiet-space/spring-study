package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {
    @Test
    void configurationTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository1 = " + memberRepository1); // @3697186
        System.out.println("orderService -> memberRepository2 = " + memberRepository2); // @3697186
        System.out.println("memberRepository = " + memberRepository); // @3697186

        // 싱글톤 패턴을 사용하면 별개의 클래스가 참조하는 인스턴스 (memberRepository) 또한 싱글톤 방식으로 호출할 수 있다
        // memberRepository 인스턴스 또한 같이 생성되는데...
        // memberRepository 생성은 최초 한 번만 발생하기 때문에, 참조할 때는 기존에 존재하던 memberRepository 를 가지고 옴.
        // 따라서 참조값 비교시 동일한 값이라고 확인됨

        Assertions.assertThat(memberRepository1).isSameAs(memberRepository);
        Assertions.assertThat(memberRepository2).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());
    }
}
