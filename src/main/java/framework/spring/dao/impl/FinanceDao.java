package framework.spring.dao.impl;

import framework.spring.utils.JdbcUtils;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class FinanceDao {

    public void addMoney(Long id, int money) throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement preparedStatement = connection
                .prepareStatement("update tbl_employee set salary = salary + ? where id = ?");
        preparedStatement.setInt(1, money);
        preparedStatement.setLong(2, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }

    public void subtractMoney(Long id, int money) throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement preparedStatement = connection
                .prepareStatement("update tbl_employee set salary = salary - ? where id = ?");
        preparedStatement.setInt(1, money);
        preparedStatement.setLong(2, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }
}
