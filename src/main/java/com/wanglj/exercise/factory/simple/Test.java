package com.wanglj.exercise.factory.simple;

/**
 * @author Wanglj
 * @date 2021/7/21 17:49
 */
public class Test {

    public static void main(String[] args) {
       /* Car car = CarFactory.getBiyadiCar();
        Car car2 = CarFactory.getTeslaCar();*/

        Car car1 = CarFactory.buyCar(1);
        Car car2 = CarFactory.buyCar(2);
        car1.getCar();
        car2.getCar();
    }
}
