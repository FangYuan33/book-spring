package framework.spring.pojo;

import framework.spring.pojo.base.Toy;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Car extends Toy {

    public Car(String name) {
        super(name);
    }
}
