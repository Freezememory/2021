package com.wanglj.exercise.decorator;

/**
 * @author Wanglj
 * @date 2021/7/23 15:28
 */
public class Bacon extends Decorator {

    public Bacon(Food food) {
        super(food, 4, "培根");
    }


    public float cost() {
        return getFree() + getFood().cost();
    }

    @Override
    public String getDesc() {
        return super.getDesc() + getFood().getDesc();
    }
}
