package hello.core;

// 컴포넌트 스캔 & 의존관계 자동 주입

import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration // 싱글톤
@ComponentScan( // 컴포넌트 스캔
        // basePackages = "hello.core.member", // 컴포넌트 스캔 패키지 시작 위치 (배열 지정 가능)
        // basePackageClasses = AutoAppConfig.class, // 컴포넌트 스캔 시작 패키지 지정 (배열 지정 가능)
        // 위 지정 안 하면, 컴포넌트 스캔을 설정한 AutoAppConfig을 시작으로 삼는다

        // @Configuration 도 스캔의 대상임 -> 내부에 @Component 등록되어 있음
        excludeFilters =  @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) // 스캔 시 () 내부 값은 제외
)
public class AutoAppConfig {
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
