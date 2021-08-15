package com.wanglj.exercise.aop.aspect;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 切面获取入参和出参
 */
@Component
@Aspect
@Slf4j
public class CustomAspect {

    @Pointcut("@within(org.springframework.stereotype.Controller) " +
            "|| @within(org.springframework.web.bind.annotation.RestController)")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object handle(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String uri = request.getRequestURI().replace(request.getContextPath(), "");
        Object result = joinPoint.proceed();
        //if (uri.indexOf(BaseUriHead.API) != -1 || uri.indexOf(BaseUriHead.APPLETS) != -1) {
        String inPutParam = preHandle(joinPoint, request);
        String outPutParam = postHandle(result);
        String ip = getRemoteHost(request);
        log.info("remoteIP:{},uri:{},inPutParam:{},outPutParam:{}", ip, uri, inPutParam, outPutParam);
        // }
        return result;
    }

    private String preHandle(ProceedingJoinPoint joinPoint, HttpServletRequest request) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        Annotation[] annotations = targetMethod.getAnnotations();
        StringBuffer sb = new StringBuffer();
        String token = request.getHeader("token");
        if (StringUtils.isNotBlank(token)) {
            sb.append("heander-token:" + token + ",");
        }
        for (Annotation annotation : annotations) {
            log.info("1-{}", annotation.annotationType().toString());
            if (annotation.annotationType().toString().indexOf("org.springframework.web.bind.annotation") == -1) {
                continue;
            }
            sb.append(JSON.toJSONString(request.getParameterMap()));
        }
        return sb.toString();
    }

    /**
     * 返回数据
     *
     * @param retVal
     * @return
     */
    private String postHandle(Object retVal) {
        if (null == retVal) {
            return "";
        }
        return JSON.toJSONString(retVal);
    }

    private String getRemoteHost(HttpServletRequest request) {
        String unknown = "unknown";
        String ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isBlank(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

}


