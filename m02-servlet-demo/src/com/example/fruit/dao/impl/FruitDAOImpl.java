package com.example.fruit.dao.impl;

import com.example.fruit.dao.FruitDAO;
import com.example.fruit.pojo.Fruit;
import com.example.myssm.basedao.BaseDAO;

import java.util.List;

public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {

    @Override
    public List<Fruit> getFruitPageList(Integer page) {
        //SELECT * FROM t_fruit LIMIT (page - 1) * 5 , 5 ;
        return super.executeQuery("select * from t_fruit limit ? , 5", (page - 1) * 5);
    }

    @Override
    public List<Fruit> getFruitList() {
        return super.executeQuery("select * from t_fruit");
    }

    @Override
    public Fruit getFruitByFid(Integer fid) {
        return super.load("select * from t_fruit where fid = ?", fid);
    }

    @Override
    public void updateFruit(Fruit fruit) {
        String sql = "update t_fruit set fname = ? , price = ? , fcount = ? , remark = ? where fid = ?";
        super.executeUpdate(sql, fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark(), fruit.getFid());
    }

    @Override
    public void delFruit(Integer fid) {
        super.executeUpdate("delete from t_fruit where fid = ? ", fid);
    }

    @Override
    public void addFruit(Fruit fruit) {
        super.executeUpdate("insert into t_fruit values(0, ?, ?, ?, ?)", fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark());
    }

    @Override
    public int getFruitCount() {
        return ((Long) super.executeComplexQuery("select count(*) from t_fruit")[0]).intValue();
    }
}
