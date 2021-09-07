package com.wanglj.exercise.aop.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Wanglj
 * @date 2021/9/4 14:44
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestReq {

    private LocalDateTime dateTime;

    private String name;


/*
    public String getName() {
        return "有点意思";
    }*/

    public static void main(String[] args) {
        TestReq req = TestReq.builder().name("abc").dateTime(LocalDateTime.now()).build();
        //req.setDateTime(LocalDateTime.now());
        System.out.println(req.toString());
    }
}
