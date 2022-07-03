package framework.spring.pojo.base;

import lombok.Data;

@Data
public abstract class Toy {

    private String name;

    protected Toy(String name) {
        System.out.println("生产一个玩具:" + name);
        this.name = name;
    }
}
