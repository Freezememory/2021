package com.wanglj.exercise;

import com.wanglj.exercise.aop.service.AopTestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Wanglj
 * @date 2021/8/2 23:28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestExerciseApplication {

    @Autowired
    private AopTestService aopTestService;

    @Test
    public void aopTest() {
        aopTestService.testAop("wanglj", 23);
    }
}
