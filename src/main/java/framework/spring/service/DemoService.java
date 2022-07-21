package framework.spring.service;

import java.util.List;

public interface DemoService {

    List<String> findAll();

    /**
     * +积分
     */
    int add(String userId, int points);

    /**
     * -积分
     */
    int subtract(String userId, int points);

    /**
     * ×积分
     */
    int multiply(String userId, int points);

    /**
     * ÷积分
     */
    int divide(String userId, int points);
}
