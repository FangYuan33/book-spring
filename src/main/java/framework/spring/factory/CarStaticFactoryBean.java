package framework.spring.factory;

import framework.spring.pojo.Car;

/**
 * Car的静态工厂
 */
public class CarStaticFactoryBean {

    public static Car getCar() {
        return new Car("car");
    }
}
