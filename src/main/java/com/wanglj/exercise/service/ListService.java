package com.wanglj.exercise.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: Wanglj
 * @Date: 2021/9/8 16:17
 * @Description :
 */
@Slf4j
@Service
public class ListService {

/*    public static void main(String[] args) {
        ListService service = new ListService();
        service.listTest();
    }*/

    @Scheduled(cron = "0/1 * * * * ?")
    public void listTest() {
        List<String> list = new LinkedList<>();
        list.add("1234");
        list.add("12345");
        for (String s : list) {
            log.info("测试前：{}", s);
        }
        list.remove(0);
        System.out.println("--------");
        for (String s : list) {
            log.info("测试后：{}", s);
        }
    }
}
