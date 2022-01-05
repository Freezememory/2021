package com.wanglj.exercise.Template;

/**
 * @Author: Wanglj
 * @Date: 2022/1/5 21:11
 * @Description :
 */
public class Receiver {

    public static void main(String[] args) {

        Incident incident = new Incident();
        incident.setName("lisq");
        incident.setAge(24);
        incident.setAddress("hunan");
        incident.setCountry("China");

        MainTemplate mainTemplate1 = new SubTemplate1();
        MainTemplate mainTemplate2 = new SubTemplate2();
        MainTemplate mainTemplate3 = new SubTemplate3();
        MainTemplate mainTemplate4 = new SubTemplate1.SubTemplate4();

        mainTemplate1.mainProcess(incident);
        mainTemplate2.mainProcess(incident);
        mainTemplate3.mainProcess(incident);
        mainTemplate4.mainProcess(incident);
    }
}
