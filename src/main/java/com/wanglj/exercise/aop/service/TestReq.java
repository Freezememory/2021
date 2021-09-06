package com.wanglj.exercise.aop.service;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Wanglj
 * @date 2021/9/4 14:44
 */
@Data
public class TestReq {

    private LocalDateTime dateTime;

    private String name;

    public String getName() {
        return "有点意思";
    }

    public static void main(String[] args) {
        TestReq req = new TestReq();
        req.setDateTime(LocalDateTime.now());
        System.out.println(req.toString());
    }
}
