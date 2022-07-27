package framework.spring.dao.impl;

import framework.spring.dao.BaseDao;
import framework.spring.dao.UserDao;
import framework.spring.pojo.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public List<User> selectAll() {
        return jdbcTemplate.query("select * from tbl_user", new BeanPropertyRowMapper<>(User.class));
    }
}
