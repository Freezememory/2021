package com.wanglj.exercise.decorator;

/**
 * @author Wanglj
 * @date 2021/7/23 15:28
 */
public class Egg extends Decorator {

    public Egg(Food food) {
        super(food, 2, "鸡蛋");
    }


    public float cost() {
        return getFree() + getFood().cost();
    }


    @Override
    public String getDesc() {
        return super.getDesc() + getFood().getDesc();
    }
}
