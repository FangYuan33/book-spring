package framework.spring.dao.impl;

import framework.spring.dao.AccountDao;
import framework.spring.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl extends BaseDao implements AccountDao {

    @Override
    public void addMoney(Integer userId, int money) {
        jdbcTemplate.update("update tbl_account set money = money + ? where user_id = ?", money, userId);
    }

    @Override
    public void subtractMoney(Integer userId, int money) {
        jdbcTemplate.update("update tbl_account set money = money - ? where user_id = ?", money, userId);
    }
}
