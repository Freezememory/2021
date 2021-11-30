package com.wanglj.exercise.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * @Author: Wanglj
 * @Date: 2021/11/29 14:58
 * @Description :
 */
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class DeviceExcel {
    /**
     * 强制读取第三个 这里不建议 index 和 name 同时用，要么一个对象只用index，要么一个对象只用name去匹配
     */
/*    @ExcelProperty(index = 2)
    private Double doubleData;*/
    /**
     * 用名字去匹配，这里需要注意，如果名字重复，会导致只有一个字段读取到数据
     */
/*    @ExcelProperty("字符串标题")
    private String string;*/
/*    @ExcelProperty("日期标题")
    private Date date;*/
    @ExcelProperty(index = 1)
    private String sbbh;
    @ExcelProperty(index = 2)
    private String lhybh;
    @ExcelProperty(index = 3)
    private String yjydbh;
    @ExcelProperty(index = 4)
    private String yjydbh2;
    @ExcelProperty(index = 5)
    private String deviceType;
    @ExcelProperty(index = 6)
    private String unitNo;
    @ExcelProperty(index = 7)
    private String unitName;
    @ExcelProperty(index = 8)
    private String deviceCompany;
    @ExcelProperty(index = 9)
    private String pointName;
    @ExcelProperty(index = 10)
    private String ldCode;
    @ExcelProperty(index = 11)
    private String lkCode;
    @ExcelProperty(index = 12)
    private String kilometer;
    @ExcelProperty(index = 13)
    private String fx;
    @ExcelProperty(index = 14)
    private String longitude;
    @ExcelProperty(index = 15)
    private String latitude;
    @ExcelProperty(index = 16)
    private String laneNo;
    @ExcelProperty(index = 17)
    private String driverSign;
    @ExcelProperty(index = 18)
    private String fxDesc;
    @ExcelProperty(index = 19)
    private String terminalNo;
    @ExcelProperty(index = 20)
    private String terminalPort;
    @ExcelProperty(index = 21)
    private String terminalUsername;
    @ExcelProperty(index = 22)
    private String terminalPass;
    @ExcelProperty(index = 23)
    private String deviceChannelNo;
    @ExcelProperty(index = 24)
    private String ip;
    @ExcelProperty(index = 25)
    private String username;
    @ExcelProperty(index = 26)
    private String password;


}
