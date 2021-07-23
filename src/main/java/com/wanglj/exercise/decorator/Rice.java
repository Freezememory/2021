package com.wanglj.exercise.decorator;

/**
 * @author Wanglj
 * @date 2021/7/23 15:21
 */
public class Rice extends Food {


    public Rice() {
        super(10, "炒米饭");
    }

    public float cost() {
        return getFree();
    }


}
