package com.wanglj.exercise.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wanglj
 * @date 2021/7/21 16:11
 * 测试controller
 */
@RestController
public class TestController {


    @RequestMapping(value = "/test")
    public String test() {
        String a = "hello  springBoot!!!!";
        System.out.println("springboot 启动！");
        return a;
    }
}
