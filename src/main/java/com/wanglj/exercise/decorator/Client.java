package com.wanglj.exercise.decorator;

/**
 * @author Wanglj
 * @date 2021/7/23 15:31
 */
public class Client {

    public static void main(String[] args) {
        Food food = new Rice();
        //food.desc();
        System.out.println(food.getDesc() + "  " + food.cost() + "元");

        food = new Egg(food);
        System.out.println(food.getDesc() + "  " + food.cost() + "元");


        food = new Bacon(food);
        System.out.println(food.getDesc() + "  " + food.cost() + "元");


        Food food2 = new Noodles();
        //food.desc();
        System.out.println(food2.getDesc() + "  " + food2.cost() + "元");

        food2 = new Bacon(food2);
        System.out.println(food2.getDesc() + "  " + food2.cost() + "元");
    }
}
