package com.example.jdbc.learn;

import com.example.jdbc.bean.Fruit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Demo03Query {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String ssl_config = "?createDatabaseIfNotExist=true&useSSL=false";
        Demo03Query test = new Demo03Query();

        //1. 加载驱动
        Class.forName("com.mysql.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/db1" + ssl_config;
        //2. 通过驱动管理器获取连接对象
        Connection conn = DriverManager.getConnection(url, "root", "1234");


        test.queryAll(conn);
        //test.findFruit(conn, 33);
        //test.getFruitCount(conn);

        conn.close();

    }

    private void queryAll(Connection conn) throws SQLException {
        String sql = "select * from t_fruit";
        PreparedStatement psmt = conn.prepareStatement(sql);

        ResultSet rs = psmt.executeQuery();

        List<Fruit> fruitList = new ArrayList<>();
        while (rs.next()) {

            //表示读取当前行的第一列数据
            //int id = resultSet.getInt("fid");  //参数也可以是结果集的列名
            int fid = rs.getInt(1);

            String fname = rs.getString(2);
            int price = rs.getInt(3);
            int fcount = rs.getInt(4);
            String remark = rs.getString(5);

            Fruit fruit = new Fruit(fid, fname, price, fcount, remark);
            fruitList.add(fruit);
        }
        rs.close();
        psmt.close();

        fruitList.forEach(System.out::println);
    }

    private void findFruit(Connection conn, int id) throws SQLException {
        String sql = "select * from t_fruit where fid = ?";
        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setInt(1, id);

        ResultSet rs = psmt.executeQuery();
        if (rs.next()) {

            int fid = rs.getInt(1);
            String fname = rs.getString(2);
            int price = rs.getInt(3);
            int fcount = rs.getInt(4);
            String remark = rs.getString(5);

            Fruit fruit = new Fruit(fid, fname, price, fcount, remark);
            System.out.println(fruit);
        }
        rs.close();
        psmt.close();
    }

    private void getFruitCount(Connection conn) throws SQLException {
        String sql = "select count(*) from t_fruit";
        PreparedStatement psmt = conn.prepareStatement(sql);

        ResultSet rs = psmt.executeQuery();
        if (rs.next()) {

            int count = rs.getInt(1);

            System.out.println("总记录条数： " + count);
        }
        rs.close();
        psmt.close();
    }
}
