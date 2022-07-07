package com.example.myssm.basedao;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnUtil {
//    public static final String SSL_CONFIG = "?createDatabaseIfNotExist=true&useSSL=false";
//    public static final String DRIVER = "com.mysql.jdbc.Driver";
//    public static final String URL = "jdbc:mysql://localhost:3306/zonedb1" + SSL_CONFIG;
//    public static final String USER = "root";
//    public static final String PWD = "1234";

    public static String DRIVER;
    public static String URL;
    public static String USER;
    public static String PWD;

    static {
        InputStream is = ConnUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();

        try {
            properties.load(is);
            DRIVER = properties.getProperty("jdbc.driver");
            URL = properties.getProperty("jdbc.url");
            USER = properties.getProperty("jdbc.user");
            PWD = properties.getProperty("jdbc.pwd");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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
