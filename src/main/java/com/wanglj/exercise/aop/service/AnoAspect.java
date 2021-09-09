package com.wanglj.exercise.aop.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wanglj.exercise.aop.aspect.AopTest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author: Wanglj
 * @Date: 2021/9/9 16:43
 * @Description :
 */
@Component
@Aspect
@Order(1)
@Slf4j
public class AnoAspect {


    @Pointcut(value = "@annotation(aopTest)")
    public void myPoint(AopTest aopTest) {

    }

    @Around(value = "myPoint(aopTest)")
    public Object aopTestAround(ProceedingJoinPoint pjp, AopTest aopTest) throws Throwable {
        //MethodSignature signature = (MethodSignature)pjp.getSignature();

        //AopTest annotation = signature.getMethod().getAnnotation(AopTest.class);
        //String anoMethodName = annotation.method();
        String anoMethodName = aopTest.method();

        //Map map = getFieldsName(pjp);
        //TestReq req = (TestReq) map.get("req");
        String className = pjp.getTarget().getClass().toString();
        String methodName = pjp.getSignature().getName();
        Object[] array = pjp.getArgs();
        TestReq req = (TestReq) array[0];
        log.info("注解测试类：{}", req.toString());
        ObjectMapper mapper = new ObjectMapper();
        log.info("注解调用前：{}类  ： {}方法  传递参数为 ：{}  ", className, methodName, mapper.writeValueAsString(array));
        log.info("注解方法属性：{}", anoMethodName);
        Object object = pjp.proceed();
        log.info("注解调用后：{}类  ： {}方法  返回值 ：{}  ", className, methodName, mapper.writeValueAsString(object));
        return object;
    }
/*
    @AfterReturning(value = "myPoint(aopTest)")
    public void afterReturn(ProceedingJoinPoint pjp, AopTest aopTest) {
        String method = aopTest.method();
    }*/


}
