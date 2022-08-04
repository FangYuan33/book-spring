package mvc.dao.impl;

import mvc.dao.BaseDao;
import mvc.dao.UserDao;
import mvc.pojo.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public List<User> selectAll() {
        return jdbcTemplate.query("select * from tbl_user", new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public User selectById(Integer id) {
        List<User> userList = jdbcTemplate.query("select * from tbl_user where id = ?",
                new BeanPropertyRowMapper<>(User.class), id);

        return userList.size() > 0 ? userList.get(0) : null;
    }

    @Override
    public void save(User user) {
        if (user.getName() == null || user.getTel() == null) {
            throw new IllegalArgumentException("参数异常，哈哈哈");
        }

        jdbcTemplate.update("insert into tbl_user (name, tel) values (?, ?)", user.getName(), user.getTel());
    }
}
