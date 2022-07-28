package framework.spring.service;

public interface AccountService {

    void transfer(Integer sourceId, Integer targetId, Integer money);
}
