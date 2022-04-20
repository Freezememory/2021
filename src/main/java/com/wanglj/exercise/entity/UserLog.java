package com.wanglj.exercise.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @Author: Wanglj
 * @Date: 2022/3/21 19:54
 * @Description :
 */
@Data
@ApiModel(value = "用户日志信息表")
@EqualsAndHashCode(callSuper = true)
@TableName("user_log")
public class UserLog extends Model<UserLog> {

    @ApiModelProperty("用户标识")
    @TableId
    private String id;

    @ApiModelProperty("用户标识用户唯一标识")
    private String userId;

    @ApiModelProperty("用户姓名")
    private String userName;

    @ApiModelProperty("性别性别 1-男 2-女")
    private String sex;

    @ApiModelProperty("办公电话")
    private String officePhone;

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("用户类型1.民警 2.辅警")
    private String type;

    @ApiModelProperty("身份证号")
    private String cardNo;

    @ApiModelProperty("警号")
    private String policeId;

    @ApiModelProperty("所属机构")
    private String orgId;

    @ApiModelProperty("所在地区所在区域区域编码")
    private String area;

    @ApiModelProperty("更新时间;yyy-MM-dd HH:mm:ss")
    private String updateTime;

    @ApiModelProperty("新增时间;yyy-MM-dd HH:mm:ss")
    private String createTime;

    @ApiModelProperty("采集时间;yyy-MM-dd HH:mm:ss")
    private String time;

    @ApiModelProperty("机构名称组织机构名称")
    private String orgName;

    @ApiModelProperty("上级机构标识上级组织机构id")
    private String parentOrgId;

}
