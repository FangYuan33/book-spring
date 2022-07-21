package framework.spring.service;

import java.util.List;

public interface DemoService {

    List<String> findAll();

    /**
     * +积分
     */
    int add(String userId, int points);
}
