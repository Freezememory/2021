package com.wanglj.exercise.decorator;

/**
 * @author Wanglj
 * @date 2021/7/23 15:25
 */
public class Noodles extends Food {

    public Noodles() {
        super(14, "米粉");
    }

    public float cost() {
        return getFree();
    }
}
