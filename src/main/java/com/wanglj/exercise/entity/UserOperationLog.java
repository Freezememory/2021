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
 * @Date: 2022/3/21 19:12
 * @Description :
 */
@Data
@ApiModel(value = "用户操作日志信息表")
@EqualsAndHashCode(callSuper = true)
@TableName("user_operation_log")
public class UserOperationLog extends Model<UserOperationLog> {

    @ApiModelProperty("用户标识")
    @TableId
    private String id;

    @ApiModelProperty("警号")
    private String policeId;

    @ApiModelProperty("证书sn")
    private String sn;

    @ApiModelProperty("身份证号")
    private String cardNo;

    @ApiModelProperty("请求标识")
    private String requestId;

    @ApiModelProperty("会话标识")
    private String sessionId;

    @ApiModelProperty(" 终端IP")
    private Integer terminalIp;

    @ApiModelProperty("集中管控系统中应用唯一标识")
    private String source;

    @ApiModelProperty("日志类型")
    private String logType;

    @ApiModelProperty("功能模块")
    private String module;

    @ApiModelProperty("请求参数(格式化)")
    private String formatParam;

    @ApiModelProperty(" 访问url")
    private float url;

    @ApiModelProperty("访问内容")
    private Date content;

    @ApiModelProperty("响应内容")
    private Date response;

    @ApiModelProperty("响应内容类型")
    private Date responseType;

    @ApiModelProperty("设备IMEI号")
    private String imei;

    @ApiModelProperty("所属业务")
    private String business;

    @ApiModelProperty("业务扩展属性")
    private String extend;

    @ApiModelProperty("请求耗时（ms）")
    private Long responseTime;

    @ApiModelProperty("访问结果")
    private String result;

    @ApiModelProperty("失败原因")
    private String errorCode;

    @ApiModelProperty("失败日志")
    private String errorLog;

}
