package com.example.jdbc.fruitshop.dao.impl;

import com.example.jdbc.bean.Fruit;
import com.example.jdbc.fruitshop.dao.FruitDAO;
import com.example.jdbc.fruitshop.dao.base.BaseDAO;

import java.util.List;

public class FruitImpl extends BaseDAO<Fruit> implements FruitDAO {

    @Override
    public List<Fruit> getFruitList() {
        String sql = "select * from t_fruit";
        return super.executeQuery(sql);
    }

    @Override
    public boolean addFruit(Fruit fruit) {
        String sql = "insert into t_fruit values(0, ?, ?, ?, ?)";
        int count = super.executeUpdate(sql, fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark());

        //insert语句返回的是自增列，而不是影响行数
        //System.out.println("自增键：" + count);
        return count > 0;
    }


    @Override
    public boolean updateFruit(Fruit fruit) {
        String sql = "update t_fruit set fcount = ? where fid = ?";
        return super.executeUpdate(sql, fruit.getFcount(), fruit.getFid()) > 0;
    }

    @Override
    public Fruit getFruitByName(String name) {
        String sql = "select * from t_fruit where fname = ?";
        return super.load(sql, name);

    }

    @Override
    public boolean deleteFruit(String name) {
        String sql = "delete from t_fruit where fname like ?";
        return super.executeUpdate(sql, name) > 0;
    }
}
