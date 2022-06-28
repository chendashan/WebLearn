package com.example.jdbc.learn;

import com.example.jdbc.bean.Fruit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Demo02 {
    public static final String SSL_CONFIG = "?createDatabaseIfNotExist=true&useSSL=false";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1. 加载驱动
        Class.forName("com.mysql.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/db1" + SSL_CONFIG;
        //2. 通过驱动管理器获取连接对象
        Connection conn = DriverManager.getConnection(url, "root", "1234");

        Demo02 test = new Demo02();
        test.deleteFruit(conn);
    }

    private void addFruit(Connection conn) throws SQLException {

        //3. 编写sql语句  ? 表示占位符
        // id (自增键), fname, price , fcount , remark
        String sql = "insert into t_fruit values(0, ?, ?, ?, ?)";

        //4. 创建预处理命令对象
        PreparedStatement psmt = conn.prepareStatement(sql);
        //5. 填充参数
        psmt.setString(1, "榴莲2");
        psmt.setInt(2, 14);
        psmt.setInt(3, 5);
        psmt.setString(4, "皮薄馅大");

        //6. 执行更新（增删改），返回影响行数
        int count = psmt.executeUpdate();
        System.out.println(count > 0 ? "添加成功" : "添加失败");

        //7. 释放资源（关闭连接）
        psmt.close();
        conn.close();
    }

    private void updateFruit(Connection conn) throws SQLException {
        Fruit fruit = new Fruit();
        fruit.setFid(33);
        fruit.setFname("猕猴桃");
        fruit.setRemark("产地拉斯维加斯");

        String sql = "update t_fruit set fname = ? , remark = ? where fid = ?";

        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setString(1, fruit.getFname());
        psmt.setString(2, fruit.getRemark());
        psmt.setInt(3, fruit.getFid());

        int count = psmt.executeUpdate();
        System.out.println(count > 0 ? "修改成功" : "修改失败");

        psmt.close();
        conn.close();
    }

    private void deleteFruit(Connection conn) throws SQLException {

        String sql = "delete from t_fruit where fid = ?";

        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setInt(1, 6);

        int count = psmt.executeUpdate();
        System.out.println(count > 0 ? "删除成功" : "删除失败");

        psmt.close();
        conn.close();
    }
}
