package com.example.fruit.dao.impl;

import com.example.fruit.dao.FruitDAO;
import com.example.fruit.pojo.Fruit;
import com.example.myssm.basedao.BaseDAO;

import java.util.List;

public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {

    @Override
    public List<Fruit> getFruitList() {
        return super.executeQuery("select * from t_fruit");
    }
}
