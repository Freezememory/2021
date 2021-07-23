package com.wanglj.exercise.decorator;

/**
 * @author Wanglj
 * @date 2021/7/23 15:54
 */
public abstract class Decorator extends Food {

    private Food food;

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Decorator(Food food, float free, String desc) {
        super(free, desc);
        this.food = food;
    }

}
