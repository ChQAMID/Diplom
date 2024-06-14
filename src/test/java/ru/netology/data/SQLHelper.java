package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;


public class SQLHelper {
    private static final QueryRunner QUERY_RUNNER = new QueryRunner();

    private SQLHelper() {
    }

    @SneakyThrows
    private static Connection getConn() {
        return DriverManager.getConnection(
                System.getProperty("db.url"),
                System.getProperty("db.username"),
                System.getProperty("db.password")
        );
    }

    @SneakyThrows
    public static int getDebitSumSQL() {
        var sum = "SELECT amount FROM payment_entity ORDER BY created DESC LIMIT 1";
        var conn = getConn();
        return QUERY_RUNNER.query(conn, sum, new ScalarHandler<>());
    }


    @SneakyThrows
    public static String getDebitStatusSQL() {
        var status = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        var conn = getConn();
        return QUERY_RUNNER.query(conn, status, new ScalarHandler<>());
    }

    @SneakyThrows
    public static String getCreditStatusSQL() {
        var status = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        var conn = getConn();
        return QUERY_RUNNER.query(conn, status, new ScalarHandler<>());
    }

    @SneakyThrows
    public static void cleanDataBase() {
        var connection = getConn();
        QUERY_RUNNER.execute(connection, "DELETE FROM payment_entity");
        QUERY_RUNNER.execute(connection, "DELETE FROM credit_request_entity");
    }
}
