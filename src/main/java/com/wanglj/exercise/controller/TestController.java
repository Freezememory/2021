package com.wanglj.exercise.controller;

import com.alibaba.excel.EasyExcel;
import com.wanglj.exercise.aop.service.AopTestService;
import com.wanglj.exercise.aop.service.TestReq;
import com.wanglj.exercise.excel.DeviceExcel;
import com.wanglj.exercise.excel.DeviceListenerExcel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Wanglj
 * @date 2021/7/21 16:11
 * 测试controller
 */
@RestController
public class TestController {
    @Autowired
    private AopTestService aopTestService;


    @RequestMapping(value = "/test")
    public String test() {
        String a = "hello  springBoot!!!!";
        System.out.println("springboot 启动！");
        return a;
    }

    @RequestMapping(value = "/aopTest")
    public String aopTest() {
        TestReq testReq = new TestReq();
        testReq.setDateTime(LocalDateTime.now());
        //testReq.setName("test");
        return aopTestService.testAop(testReq, 23);
    }


    /**
     * 文件上传
     * <p>1. 创建excel对应的实体对象
     * <p>2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，
     */
    @PostMapping("uploadDeviceExcel")
    @ResponseBody
    public String upload(MultipartFile file) throws IOException {
        List<DeviceExcel> list = new ArrayList<>();
        EasyExcel.read(file.getInputStream(), DeviceExcel.class, new DeviceListenerExcel(list)).sheet().doRead();
        Map<String, List<DeviceExcel>> terminalMap = list.stream().filter(item -> StringUtils.isNotBlank(item.getTerminalNo())).collect(Collectors.groupingBy(DeviceExcel::getTerminalNo));
        for (Map.Entry<String, List<DeviceExcel>> entry : terminalMap.entrySet()) {
            System.out.println("=====================" + entry.getKey());
            for (DeviceExcel deviceExcel : entry.getValue()) {
                System.out.println(deviceExcel.toString());
            }
        }
        return "success";
    }


}
