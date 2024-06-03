package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
    private static final QueryRunner QueryRunner = new QueryRunner();

    private SQLHelper() {
    }

    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
    }

    @SneakyThrows
    public static int getSumSQL() {
        var sum = "SELECT amount FROM payment_entity order by created desc LIMIT 1";
        var conn = getConn();
        var result = QueryRunner.query(conn, sum, new ScalarHandler<Integer>());
        return result;
    }
}
