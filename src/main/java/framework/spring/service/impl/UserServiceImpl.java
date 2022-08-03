package framework.spring.service.impl;

import framework.spring.dao.UserDao;
import framework.spring.pojo.User;
import framework.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public void saveAndQuery(User user) {
        // 这样实现就能在保证事务正常回滚了
        transactionTemplate.execute(transactionStatus -> {
            userDao.save(user);

            int breakHere = 1 / 0;

            System.out.println(userDao.selectById(1));

            return null;
        });
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.selectAll();
    }
}
