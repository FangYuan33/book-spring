package framework.spring.service.impl;

import framework.spring.dao.AccountDao;
import framework.spring.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    @Transactional
    public void transfer(Integer sourceId, Integer targetId, Integer money) {
        System.out.println(TransactionSynchronizationManager.getCurrentTransactionName());
        accountDao.subtractMoney(sourceId, money);

        int i = 1 / 0;

        accountDao.addMoney(targetId, money);
    }
}
