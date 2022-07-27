package framework.spring.dao;

import framework.spring.pojo.User;

import java.util.List;

public interface UserDao {

    List<User> selectAll();
}
