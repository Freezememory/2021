package com.wanglj.exercise.aop.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author Wanglj
 * @date 2021/8/2 23:10
 */
@Component
@Aspect
@Slf4j
public class AopAspect {


    @Pointcut(value = "execution( * com.wanglj.exercise.aop.service.*.*(..))")
    public void myPoint() {

    }

    @Around("myPoint()")
    public Object aopTestAround(ProceedingJoinPoint pjp) throws Throwable {
        String className = pjp.getTarget().getClass().toString();
        String methodName = pjp.getSignature().getName();
        Object[] array = pjp.getArgs();
        ObjectMapper mapper = new ObjectMapper();
        log.info("调用前：{}类  ： {}方法  传递参数为 ：{}  ", className, methodName, mapper.writeValueAsString(array));
        Object object = pjp.proceed();
        log.info("调用后：{}类  ： {}方法  返回值 ：{}  ", className, methodName, mapper.writeValueAsString(object));
        return object;
    }
}
