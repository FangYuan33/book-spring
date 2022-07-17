package framework.spring.pojo;

import lombok.Data;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.Lifecycle;

@Data
public class Person implements InitializingBean, DisposableBean, Lifecycle {

    private String name;

    private Integer age;

    private Boolean state = false;

    public Person() {
        System.out.println("Person constructor RUN...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Person afterPropertiesSet RUN...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Person destroy RUN...");
    }

    @Override
    public void start() {
        state = true;
        System.out.println("Person 起床了...");
    }

    @Override
    public void stop() {
        state = false;
        System.out.println("Person 休息了...");
    }

    @Override
    public boolean isRunning() {
        return state;
    }
}
