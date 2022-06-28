package com.example.jdbc.learn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Demo01 {

    /* 不建议在没有服务器身份验证的情况下建立SSL连接。根据MySQL 5.5.45+、5.6.26+和5.7.6+的要求，如果未设置explicit选项，
    则默认情况下必须建立SSL连接。为了符合不使用SSL的现有应用程序，verifyServerCertificate属性设置为“false”。您需要通过
    设置useSSL=false显式禁用SSL，或者设置useSSL=true并为服务器证书验证提供truststore。
    */
    public static final String SSL_CONFIG = "?createDatabaseIfNotExist=true&useSSL=false";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1. 添加jar

        //2. 加载驱动
        Class.forName("com.mysql.jdbc.Driver");

        //3. 通过驱动管理器获取连接对象
        //3-1. 准备URL
        String url = "jdbc:mysql://localhost:3306/db1" + SSL_CONFIG;
        //3-2. 准备用户名
        String name = "root";
        //3-3. 准备密码
        String pwd = "1234";

        Connection conn = DriverManager.getConnection(url, name, pwd);

        System.out.println("conn = " + conn);
    }
}
