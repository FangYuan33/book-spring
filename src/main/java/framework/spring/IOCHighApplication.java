package framework.spring;

import framework.spring.properties.JdbcProperties;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

public class IOCHighApplication {

    public static void main(String[] args) {
        environment();
    }

    private static void environment() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JdbcProperties.class);

        Environment environment = context.getBean(Environment.class);
        System.out.println(environment);

        System.out.println(environment.getProperty("jdbc.url"));

        StringBuilder profiles = new StringBuilder();
        for (String defaultProfile : environment.getDefaultProfiles()) {
            profiles.append(defaultProfile).append(" ");
        }
        System.out.println("Default profile: " + profiles);
    }
}
