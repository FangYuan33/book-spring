package mvc.dao;


import mvc.pojo.User;

import java.util.List;

public interface UserDao {

    List<User> selectAll();

    User selectById(Integer id);

    void save(User user);
}
