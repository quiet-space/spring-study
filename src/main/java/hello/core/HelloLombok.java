package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {
    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("dfdfdfd"); // Lombok이 만들어준 set

        String name = helloLombok.getName(); // Lombok이 만들어준 get

        System.out.println(helloLombok); // ToString
    }
}
