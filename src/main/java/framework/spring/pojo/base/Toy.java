package framework.spring.pojo.base;

import framework.spring.pojo.Person;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class Toy {

    protected Person master;

    private String name;

    protected Toy(String name) {
        System.out.println("生产一个玩具:" + name);
        this.name = name;
    }

    protected String realName;
}
