package com.example.myssm.basedao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnUtil {
    public static final String SSL_CONFIG = "?createDatabaseIfNotExist=true&useSSL=false";
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/db1" + SSL_CONFIG;
    public static final String USER = "root";
    public static final String PWD = "1234";

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    private static Connection createConn() {
        try {
            //加载驱动
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PWD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Connection getConn() {
        Connection conn = threadLocal.get();
        if (conn == null) {
            conn = createConn();
            threadLocal.set(conn);
        }
        return threadLocal.get();
    }

    public static void closeConn() throws SQLException {
        Connection conn = threadLocal.get();
        if (conn == null) {
            return;
        }
        if (!conn.isClosed()) {
            conn.close();
            threadLocal.set(null);
        }
    }
}
