package com.wanglj.exercise.service;

import com.wanglj.exercise.entity.UserLog;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: Wanglj
 * @Date: 2021/9/8 16:17
 * @Description :
 */
@Slf4j
//@Service
public class ListService {

/*    public static void main(String[] args) {
        ListService service = new ListService();
        service.listTest();
    }*/

    // @Scheduled(cron = "0/1 * * * * ?")
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
        log.error("错误信息：{error}");
    }

    public void test() {
        System.out.println("11111");
    }


    public void main1(String[] args) {
        List<UserLog> logList = new ArrayList<UserLog>();
        UserLog userLog1 = new UserLog();
        UserLog userLog2 = new UserLog();
        UserLog userLog3 = new UserLog();
        userLog1.setArea("abc1");
        userLog1.setCardNo("1");
        userLog2.setArea("abc2");
        userLog2.setCardNo("2");
        userLog3.setArea("abc3");
        userLog3.setCardNo("3");
        logList.add(userLog1);
        logList.add(userLog2);
        logList.add(userLog3);


        List<Integer> ids = Arrays.asList(1, 2, 3, 4, 5);
        List<UserLog> userList = ids
                .stream()
                .map(this::getUsersByAge)
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .collect(Collectors.toList());


        logList.stream().max(Comparator.comparing(UserLog::getArea)).get();
        Map<String, String> collect =
                logList.stream().collect(Collectors.toMap(UserLog::getArea, UserLog::getCardNo));
    }

    /**
     * @param age
     * @return
     */
    public List<UserLog> getUsersByAge(Integer age) {
        UserLog userLog1 = new UserLog();
        userLog1.setArea("abc1");
        userLog1.setCardNo("1");


        System.out.println(userLog1.hashCode());


        return Collections.singletonList(userLog1);
    }

    public static void main(String[] args) {
        UserLog userLog1 = new UserLog();
        userLog1.setArea("abc1");
        userLog1.setCardNo("1");


        System.out.println(userLog1.hashCode());
    }
}
