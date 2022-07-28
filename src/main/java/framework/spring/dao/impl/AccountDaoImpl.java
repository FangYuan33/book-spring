package framework.spring.dao.impl;

import framework.spring.dao.AccountDao;
import framework.spring.dao.BaseDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Repository
public class AccountDaoImpl extends BaseDao implements AccountDao {

    @Override
    public void addMoney(Integer userId, int money) {
        jdbcTemplate.update("update tbl_account set money = money + ? where user_id = ?", money, userId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void subtractMoney(Integer userId, int money) {
        System.out.println(TransactionSynchronizationManager.getCurrentTransactionName());

        jdbcTemplate.update("update tbl_account set money = money - ? where user_id = ?", money, userId);
    }
}
