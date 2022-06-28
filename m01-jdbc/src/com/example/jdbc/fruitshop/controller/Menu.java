package com.example.jdbc.fruitshop.controller;

import com.example.jdbc.bean.Fruit;
import com.example.jdbc.fruitshop.dao.FruitDAO;
import com.example.jdbc.fruitshop.dao.impl.FruitImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 菜单类
 */
public class Menu {
    Scanner input = new Scanner(System.in);
    FruitDAO fruitDAO = new FruitImpl();

    //显示主菜单
    public int showMainMenu() {
        System.out.println("=================欢迎使用水果库存系统=====================");
        System.out.println("1.查看水果库存列表");
        System.out.println("2.添加水果库存信息");
        System.out.println("3.查看特定水果库存信息");
        System.out.println("4.水果下架");
        System.out.println("5.退出");
        System.out.println("======================================================");
        System.out.print("请选择：");


        int slt = input.nextInt();

        return slt;

    }

    public void showFruitList() {
        List<Fruit> list = new ArrayList<>();
        list = fruitDAO.getFruitList();
        System.out.println("------------------------------------------------------");
        //FID     名称      单价         库存           备注
        System.out.println("编号\t\t名称\t\t单价\t\t库存\t\t备注");
        if (list == null || list.size() <= 0) {
            System.out.println("对不起，库存为空！");
        } else {
            for (int i = 0; i < list.size(); i++) {
                Fruit fruit = list.get(i);
                System.out.println(fruit.toString());
            }
        }
        System.out.println("------------------------------------------------------");

    }

    public void addFruit() {
        System.out.print("请输入水果名称：");
        String name = input.next();
        Fruit fruit = fruitDAO.getFruitByName(name);
        if (fruit == null) {
            //没有这个水果
            System.out.print("请输入水果单价：");
            int price = input.nextInt();
            System.out.print("请输入水果的库存量：");
            int fcount = input.nextInt();
            System.out.print("请输入水果的备注：");
            String remark = input.next();

            Fruit newFruit = new Fruit(0, name, price, fcount, remark);

            fruitDAO.addFruit(newFruit);

        } else {
            //存在这个水果
            System.out.print("请输入追加的库存量：");
            int fcount = input.nextInt();
            fruit.setFcount(fruit.getFcount() + fcount);
            fruitDAO.updateFruit(fruit);

        }
        System.out.println("添加成功！");
    }

    public void showFruitInfo() {
        System.out.print("请输入水果名称：");
        String name = input.next();
        Fruit fruit = fruitDAO.getFruitByName(name);
        if (fruit == null) {
            System.out.println("没有找到指定水果的库存记录！");
        } else {
            System.out.println("------------------------------------------------------");
            System.out.println("编号\t\t名称\t\t单价\t\t库存\t\t备注");
            System.out.println(fruit);
            System.out.println("------------------------------------------------------");
        }
    }

    public void deleteFruit() {
        System.out.print("请输入水果名称：");
        String name = input.next();
        Fruit fruit = fruitDAO.getFruitByName(name);
        if (fruit == null) {
            System.out.println("sorry，没有这个水果！！！");
        } else {
            System.out.print("是否确认下架？ （Y/N） ");
            String slt = input.next();
            if ("y".equalsIgnoreCase(slt)) {
                fruitDAO.deleteFruit(name);
                System.out.println("下架成功！！");
            }
        }
    }

    public boolean exit() {
        System.out.print("是否确认退出？(Y/N) ");
        String str = input.next();

        return !"Y".equalsIgnoreCase(str);
    }
}
