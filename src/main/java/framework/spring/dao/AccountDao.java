package framework.spring.dao;

public interface AccountDao {

    void addMoney(Integer userId, int money);

    void subtractMoney(Integer userId, int money);
}
