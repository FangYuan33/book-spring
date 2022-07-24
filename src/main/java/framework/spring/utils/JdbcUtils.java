package framework.spring.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtils {

    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/learn_spring?characterEncoding=utf8&useSSL=false";

    private static final ThreadLocal<Connection> connectThreadLocal = new ThreadLocal<>();

    public static Connection getConnection() {
        try {
            if (connectThreadLocal.get() != null) {
                return connectThreadLocal.get();
            }

            Connection connection = DriverManager.getConnection(JDBC_URL, "root", "Yilong981223");
            connectThreadLocal.set(connection);

            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void remove() {
        connectThreadLocal.remove();
    }
}
