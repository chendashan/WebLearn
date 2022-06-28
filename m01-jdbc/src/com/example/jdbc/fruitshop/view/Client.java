package com.example.jdbc.fruitshop.view;


import com.example.jdbc.fruitshop.controller.Menu;

public class Client {

    public static void main(String[] args) {
        Menu menu = new Menu();

        boolean flag = true;

        while (flag) {
            //调用显示主菜单的方法
            int slt = menu.showMainMenu();

            switch (slt) {
                case 1:
                    menu.showFruitList();
                    break;
                case 2:
                    menu.addFruit();
                    break;
                case 3:
                    menu.showFruitInfo();
                    break;
                case 4:
                    menu.deleteFruit();
                    break;
                case 5:
                    flag = menu.exit();
                    break;
                default:
                    System.out.println("暂无操作");
                    break;
            }
        }
        System.out.println("谢谢使用");
    }
}
