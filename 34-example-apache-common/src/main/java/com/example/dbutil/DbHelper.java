package com.example.dbutil;

import lombok.Data;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Data
public class DbHelper<T> {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/test";

    // Database credentials
    static final String USER = "root";
    static final String PASS = "111111";


    public static Connection getConnection() {
        Connection conn = null;

        //Step 1: Register JDBC driver
        DbUtils.loadDriver(JDBC_DRIVER);

        //Step 2: Open a connection
        System.out.println("Connecting to database...");
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static <T> T query(String sql, Class c, Object... param) {
        Connection conn = getConnection();
        ResultSetHandler<T> resultHandler = new BeanHandler<T>(c);
        QueryRunner queryRunner = getQueryRunner();
        try {
            T result = queryRunner.query(conn, sql, resultHandler, param);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DbUtils.close(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static <T> List<T> list(String sql, Class c, Object... param) {
        Connection conn = getConnection();
        ResultSetHandler<List<T>> resultHandler = new BeanListHandler<T>(c);
        QueryRunner queryRunner = getQueryRunner();
        try {
            List<T> result = queryRunner.query(conn, sql, resultHandler, param);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DbUtils.close(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static List<Map<String, Object>> list(String sql, Object... param) {
        Connection conn = getConnection();
        QueryRunner queryRunner = getQueryRunner();
        try {
            List<Map<String, Object>> result = queryRunner.query(conn, sql, new MapListHandler(), param);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DbUtils.close(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Integer upsert(String sql, Object... param) {
        Connection conn = getConnection();
        QueryRunner queryRunner = getQueryRunner();
        try {
            int insertedRecords = queryRunner.update(conn, sql, param);
            System.out.println(insertedRecords + " record(s) inserted");
            return insertedRecords;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DbUtils.close(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static int[] batch(String sql, Object[][] params) {
        Connection conn = getConnection();
        QueryRunner queryRunner = getQueryRunner();
        try {
            int[] insertedRecords = queryRunner.batch(sql, params);
            System.out.println(insertedRecords + " record(s) inserted");
            return insertedRecords;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DbUtils.close(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 批量更新
     */
    public static int[] updateBatch(String sql, List<Object[]> list) {
        int[] result = {};
        Connection conn = getConnection();
        try {
            System.out.println("Sql:" + sql);
            System.out.println("Parameters:" + Arrays.toString(list.toArray()));
            Object[][] params = list.toArray(new Object[list.size()][]);
            result = getQueryRunner().batch(conn, sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DbUtils.close(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private static QueryRunner getQueryRunner() {
        return new QueryRunner();
    }
}
