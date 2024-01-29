package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        // 1.조회: 호출할 때마다 객체 생성
        MemberService memberService1 = appConfig.memberService();
        // 2.조회: 호출할 때마다 객체 생성
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1); // @dd3b207
        System.out.println("memberService2 = " + memberService2); // @551bdc27

        // -> 순수한 DI 컨테이너의 경우, 클라이언트의 요청시마다 객체가 생성된다

        // memeberService !== memberService2
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }


    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        SingletonService singletoneService1 = SingletonService.getInstance();
        SingletonService singletoneService2 = SingletonService.getInstance();

        // 같은 객체 인스턴스가 반환된 것을 확인
        System.out.println("singletoneService1 = " + singletoneService1); // @221af3c0
        System.out.println("singletoneService2 = " + singletoneService2); // @221af3c0

        Assertions.assertThat(singletoneService1).isSameAs(singletoneService2);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 스프링 빈을 호출해서 사용하는 객체는 모두 싱글톤 패턴이 적용됨
        // 객체 (MemberService) 생성단에서는 싱글톤을 위한 로직 작성이 전혀 필요하지 않음

        // 스프링 컨테이너를 사용하면 싱글톤으로 작동한다고 이해하면 됨
        /// 스프링의 기본 빈 등록 방식은 싱글톤이지만, 다른 방식으로도 지원함 -> 빈 스코프

        // 1.조회: 호출할 때마다 객체 생성
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        // 2.조회: 호출할 때마다 객체 생성
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        // 참조값이 "같은" 것을 확인
        System.out.println("memberService1 = " + memberService1); // @36060e
        System.out.println("memberService2 = " + memberService2); // @36060e

        // -> 순수한 DI 컨테이너의 경우, 클라이언트의 요청시마다 객체가 생성된다

        // memeberService !== memberService2
        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }
}
