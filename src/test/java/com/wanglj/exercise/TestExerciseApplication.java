package com.wanglj.exercise;

import com.wanglj.exercise.aop.service.AopTestService;
import com.wanglj.exercise.aop.service.TestReq;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;


/**
 * @author Wanglj
 * @date 2021/8/2 23:28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestExerciseApplication {

    @Autowired
    private AopTestService aopTestService;

    @Test()
    public void aopTest() {
        TestReq testReq = new TestReq();
        testReq.setDateTime(LocalDateTime.now());
        // testReq.setName("test");
        aopTestService.testAop(testReq, 23);
        //aopTestService.testAop("wanglj", 23);
    }
}
