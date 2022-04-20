package com.wanglj.exercise.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


/**
 * @Author: Wanglj
 * @Date: 2022/3/21 20:01
 * @Description :
 */
@Data
@ApiModel(value = "终端操作日志信息表")
@EqualsAndHashCode(callSuper = true)
@TableName("terminal_Log")
public class TerminalLog extends Model<TerminalLog> {

    @ApiModelProperty("用户标识")
    @TableId
    private String id;

    @ApiModelProperty("用户标识")
    private String userId;

    @ApiModelProperty("设备标识")
    private String terminalId;

    @ApiModelProperty("设备类型")
    private String deviceType;

    @ApiModelProperty("设备归属")
    private String deviceOwner;

    @ApiModelProperty("设备状态")
    private String deviceState;

    @ApiModelProperty("Sim卡信息")
    private String sim;

    @ApiModelProperty("安全卡")
    private String devicecardID;

    @ApiModelProperty("变更类型")
    private String changeType;

    @ApiModelProperty("变更时间")
    private Date changeTime;

    @ApiModelProperty("终端型号")
    private String model;

    @ApiModelProperty("终端品牌")
    private String brand;

    @ApiModelProperty("手机号")
    private String telephoneNumber;

    @ApiModelProperty("imei号")
    private String imei;

    @ApiModelProperty("终端ip")
    private String terminalIp;

    @ApiModelProperty("操作系统名称")
    private String os;

    @ApiModelProperty("操作系统型号")
    private String osVersion;

    @ApiModelProperty("总存储容量")
    private String storage;

    @ApiModelProperty("CPU型号")
    private String cpuModel;

    @ApiModelProperty("序列号")
    private String seqNumber;

    @ApiModelProperty("Wifi mac")
    private String wifiMac;

    @ApiModelProperty("蓝牙 mac")
    private String bluetoothMac;

    @ApiModelProperty("激活时间")
    private Long activateTime;

    @ApiModelProperty("运行内存")
    private String runMemory;

    @ApiModelProperty("ICCID")
    private String ICCID;

    @ApiModelProperty("IMSI")
    private String Imsi;

    @ApiModelProperty("归属地")
    private String belong_to;

    @ApiModelProperty("运营商")
    private String carrier;

    @ApiModelProperty("客户端软件版本")
    private String sw_version;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("受控类型")
    private String terminal_type;

    @ApiModelProperty("屏幕")
    private String screen;

    @ApiModelProperty("摄像头")
    private String camera;

    @ApiModelProperty("传感器")
    private String sensor;

    @ApiModelProperty("主板")
    private String Mboard;

    @ApiModelProperty("内存")
    private String memory;

    @ApiModelProperty("ROM存储")
    private String Rom;

    @ApiModelProperty("扩展存储卡")
    private String Ext_memory;

    @ApiModelProperty("GPS")
    private String GPS;

    @ApiModelProperty("电池")
    private String Battery;

    @ApiModelProperty("后盖")
    private String Cover;

    @ApiModelProperty("SIM")
    private String SIM;

    @ApiModelProperty("卡槽")
    private String Card_slot;

    @ApiModelProperty("手机插孔")
    private String Phone_jack;

    @ApiModelProperty("手机附件")
    private String accessories;


}
