package com.example.fruit.service.impl;

import com.example.fruit.dao.FruitDAO;
import com.example.fruit.pojo.Fruit;
import com.example.fruit.service.FruitService;

import java.util.List;

public class FruitServiceImpl implements FruitService {

    private FruitDAO fruitDAO = null;

    @Override
    public List<Fruit> getFruitList(String keyword, Integer pageNo) {
        return fruitDAO.getFruitPageList(keyword, pageNo);
    }

    @Override
    public void addFruit(Fruit fruit) {
        fruitDAO.addFruit(fruit);
    }

    @Override
    public Fruit getFruitById(Integer fid) {
        return fruitDAO.getFruitByFid(fid);
    }

    @Override
    public void delFruit(Integer fid) {
        fruitDAO.delFruit(fid);
    }

    @Override
    public Integer getPageCount(String keyword) {
        int count = fruitDAO.getFruitCount(keyword);
        int pageCount = (count - 1) / 5 + 1;
        return pageCount;
    }

    @Override
    public void updateFruit(Fruit fruit) {
        fruitDAO.updateFruit(fruit);
    }
}
