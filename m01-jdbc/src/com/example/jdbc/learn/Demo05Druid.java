package com.example.jdbc.learn;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 连接池技术
 * <p>
 * 1. 被close的连接对象并没有真正关闭，而是将状态重新设置为空闲状态，然后放回池子，这样下次获取连接对象，这个对象会再次获取
 * 2. 没有被close的连接对象会被一致占用，那么下次继续获取连接对象，是不会获取到这个对象的（hashcode没有重复，只出现一次）
 */
public class Demo05Druid {

    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();
        InputStream is = Demo05Druid.class.getClassLoader().getResourceAsStream("jdbc.properties");
        properties.load(is);

        DruidDataSource dataSource = new DruidDataSource();

        String url = properties.getProperty("jdbc.url") + properties.getProperty("jdbc.urlConfig");
        dataSource.setDriverClassName(properties.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(url);
        dataSource.setUsername(properties.getProperty("jdbc.userName"));
        dataSource.setPassword(properties.getProperty("jdbc.pwd"));

        dataSource.setInitialSize(Integer.parseInt(properties.getProperty("jdbc.initSize")));
        dataSource.setMaxActive(Integer.parseInt(properties.getProperty("jdbc.maxActive")));
        dataSource.setMaxWait(Integer.parseInt(properties.getProperty("jdbc.maxWait")));

        //DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

        Connection conn = dataSource.getConnection();

        System.out.println(conn);

        conn.close();

    }
}
