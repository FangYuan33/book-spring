package framework.spring.factory;

import framework.spring.pojo.Ball;
import framework.spring.pojo.Car;
import framework.spring.pojo.Child;
import framework.spring.pojo.base.Toy;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ToyFactoryBean implements FactoryBean<Toy> {
    public ToyFactoryBean() {
        System.out.println("ToyFactoryBean初始化完成");
    }

    @Autowired
    private Child child;

    @Override
    public Toy getObject() {
        if ("ball".equals(child.getWantToy())) {
            return new Ball(child.getWantToy());
        } else {
            return new Car(child.getWantToy());
        }
    }

    @Override
    public Class<Toy> getObjectType() {
        return Toy.class;
    }
}
