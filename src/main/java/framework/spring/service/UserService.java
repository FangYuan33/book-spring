package framework.spring.service;

import framework.spring.pojo.User;

import java.util.List;

public interface UserService {

    void saveAndQuery(User user);

    List<User> getAllUsers();
}
