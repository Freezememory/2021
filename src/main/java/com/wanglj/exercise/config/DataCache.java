package com.wanglj.exercise.config;

import com.wanglj.exercise.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashMap;
import java.util.Map;


/**
 * @Author: Wanglj
 * @Date: 2021/11/8 16:34
 * @Description :
 */
//@Component
@Order(1)
public class DataCache implements ApplicationRunner {

    @Autowired
    private ListService listService;
    private static Map<String, Object> cacheMap = new HashMap<String, Object>();

    public static Map getCacheMap() {
        return cacheMap;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        cacheMap.put("initialCache1", "初始话程序1");
        cacheMap.put("initialCache2", "初始话程序2");
        cacheMap.put("initialCache3", "初始话程序3");
        cacheMap.put("initialCache4", "初始话程序4");
        cacheMap.put("initialCache5", "初始话程序5");
        System.out.println("项目启动就加载" + DataCache.class);
    }


    @Scheduled(fixedDelay = 10 * 1000)
    public void add() {
        listService.test();
        cacheMap.put("initialCache6", "初始话程序6");
        System.out.println("添加至内存");
    }


    @Scheduled(fixedDelay = 3 * 1000)
    public void print() {
        Map<String, Object> map = DataCache.getCacheMap();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println("键" + entry.getKey() + "-----值" + entry.getValue());
        }
        System.out.print("-----------------------");
    }


}


