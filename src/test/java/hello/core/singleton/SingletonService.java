package hello.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonService {
    private static final SingletonService instance = new SingletonService();

    // 조회시 자기가 내부적으로 만든 instance를 호출
    public static SingletonService getInstance() {
        return instance;
    }

    // 생성자
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
