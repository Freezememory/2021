package com.wanglj.exercise.factory.simple;

/**
 * @author Wanglj
 * @date 2021/7/21 17:42
 */
public class CarFactory {

/*
    public static Car getBiyadiCar(){
        return new BuildYDCar();
    }

    public static Car getTeslaCar(){
        return new TeslaCar();
    }
*/

    /**
     * kind :  1 tesla ; 其他 byd
     *
     * @param kind
     * @return
     */
    public static Car buyCar(int kind) {
        switch (kind) {
            case 1:
                return new TeslaCar();
            default:
                return new BuildYDCar();
        }
        //return null;
    }
}
