package com.wanglj.exercise.decorator;

/**
 * @author Wanglj
 * @date 2021/7/23 15:20
 */
public abstract class Food {

    private float free;

    private String desc;

    public abstract float cost();

    public float getFree() {
        return free;
    }

    public void setFree(float free) {
        this.free = free;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
// abstract float getCost();


    public Food(float free, String desc) {
        this.free = free;
        this.desc = desc;
    }

    ;

    public Food() {
    }

    ;
}
