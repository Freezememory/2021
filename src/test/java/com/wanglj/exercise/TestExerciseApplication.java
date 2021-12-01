package com.wanglj.exercise;

import com.wanglj.exercise.aop.service.AopTestService;
import com.wanglj.exercise.aop.service.TestReq;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
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
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ExerciseSpringBootApplication.class})
public class TestExerciseApplication {

    @Autowired
    private AopTestService aopTestService;

    @Autowired
    private StringEncryptor encryptor;

    @Test()
    public void aopTest() {
        TestReq testReq = new TestReq();
        testReq.setDateTime(LocalDateTime.now());
        aopTestService.testAop(testReq, 23);
    }


    @Test
    public void testJasypt() {
//        String password = "Tydic#0831";
        String password = "wanglj19980401";
        String encryptPwd = encryptor.encrypt(password);
        log.info("加密: {}", encryptPwd);
//        String d = "BPyYxVoG2Dd7s2yec1SR/JWbTR430/PCzxQ3e2qdc0ShXbNAxQKU1ED6TSx1CMJ0";
        String e = "XkpBEEDbo0SJQWmxLNZqpguYD0uFqZBrHPDPvsCWW1rfXtCc8qDT7k6JPU2vxwWc";
//        log.info("解密d: {}", encryptor.decrypt(d));
        log.info("解密e: {}", encryptor.decrypt(e));

    }

}
