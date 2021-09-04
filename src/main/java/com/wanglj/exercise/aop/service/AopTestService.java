package com.wanglj.exercise.aop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Wanglj
 * @date 2021/8/2 23:07
 */
@Slf4j
@Service
public class AopTestService {


    public String testAop(TestReq req, Integer age) {
        log.info("名字： {}", req.getName());
        log.info("年龄： {}", age);
        return req.getName() + "::" + age;
    }
}
