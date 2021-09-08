package com.wanglj.exercise.service;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: Wanglj
 * @Date: 2021/9/8 16:17
 * @Description :
 */
public class ListService {

    public static void main(String[] args) {
        ListService service = new ListService();
        service.listTest();
    }


    public String listTest() {
        List<String> list = new LinkedList<>();
        list.add("1234");
        list.add("12345");
        list.forEach(System.out::println);
        list.remove(0);
        System.out.println("--------");
        list.forEach(System.out::println);
        return null;
    }
}
