package framework.spring.pojo;

import lombok.Data;

@Data
public class Dog {

    private String name;

    private Person master;

    public Dog() {
        System.out.println("refresh create dog bean");
    }
}
