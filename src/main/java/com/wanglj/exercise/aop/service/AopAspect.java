package com.wanglj.exercise.aop.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

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
        //Map map = getFieldsName(pjp);
        //TestReq req = (TestReq) map.get("req");
        String className = pjp.getTarget().getClass().toString();
        String methodName = pjp.getSignature().getName();
        Object[] array = pjp.getArgs();
        TestReq req = (TestReq) array[0];
        log.info("测试类：{}", req.toString());
        ObjectMapper mapper = new ObjectMapper();
        log.info("调用前：{}类  ： {}方法  传递参数为 ：{}  ", className, methodName, mapper.writeValueAsString(array));
        Object object = pjp.proceed();
        log.info("调用后：{}类  ： {}方法  返回值 ：{}  ", className, methodName, mapper.writeValueAsString(object));
        return object;
    }


    private static Map getFieldsName(ProceedingJoinPoint pjp) throws NoSuchMethodException {
        Class[] parameterTypes = new Class[pjp.getArgs().length];
        Object[] args = pjp.getArgs();
        for (int i = 0; i < args.length; i++) {
            if (args[i] != null) {
                parameterTypes[i] = args[i].getClass();
            } else {
                parameterTypes[i] = null;
            }
        }
        String methodName = pjp.getSignature().getName();
        Method method = pjp.getSignature().getDeclaringType().getMethod(methodName, parameterTypes);
        // 参数名
        ParameterNameDiscoverer pnd = new DefaultParameterNameDiscoverer();
        String[] parameterNames = pnd.getParameterNames(method);
        // 通过map封装参数和参数值
        HashMap<String, Object> paramMap = new HashMap();
        for (int i = 0; i < parameterNames.length; i++) {
            paramMap.put(parameterNames[i], args[i]);
        }
        return paramMap;
    }


/*    private static Map getFieldsNameTest(ProceedingJoinPoint joinPoint) throws ClassNotFoundException, NoSuchMethodException {
        String classType = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        // 参数值
        Object[] args = joinPoint.getArgs();
        Class<?>[] classes = new Class[args.length];
        for (int k = 0; k < args.length; k++) {
            if (!args[k].getClass().isPrimitive()) {
                // 获取的是封装类型而不是基础类型
                String result = args[k].getClass().getName();
                Class s = map.get(result);
                classes[k] = s == null ? args[k].getClass() : s;
            }
        }
        ParameterNameDiscoverer pnd = new DefaultParameterNameDiscoverer();
        // 获取指定的方法，第二个参数可以不传，但是为了防止有重载的现象，还是需要传入参数的类型
        //Method method = Class.forName(classType).getMethod(methodName, classes);
        Method method = Class.forName(classType).getMethod(methodName);
        // 参数名
        String[] parameterNames = pnd.getParameterNames(method);
        // 通过map封装参数和参数值
        HashMap<String, Object> paramMap = new HashMap();
        for (int i = 0; i < parameterNames.length; i++) {
            paramMap.put(parameterNames[i], args[i]);
        }
        return paramMap;
    }*/

    private static HashMap<String, Class> map = new HashMap<String, Class>() {
        {
            put("java.lang.Integer", int.class);
            put("java.lang.Double", double.class);
            put("java.lang.Float", float.class);
            put("java.lang.Long", long.class);
            put("java.lang.Short", short.class);
            put("java.lang.Boolean", boolean.class);
            put("java.lang.Char", char.class);
        }
    };
}
