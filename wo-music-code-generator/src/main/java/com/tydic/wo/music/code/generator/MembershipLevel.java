package com.tydic.wo.music.code.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Wanglj
 * @Date: 2023/6/27 10:16
 * @Description :
 */
@ApiModel("会员等级信息")
@Data
public class MembershipLevel {

    @TableId
    private Integer id;

    @ApiModelProperty("会员等级")
    private String membershipLevel;

    @ApiModelProperty("会员卡名称")
    private String membershipName;

    @ApiModelProperty("获得条件")
    private String condition;

    @ApiModelProperty("等级权益")
    private String tierBenefits;

    @ApiModelProperty("会员人数")
    private Integer membershipNumber;

    @ApiModelProperty("状态")
    private Integer status;

    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    @ApiModelProperty("删除标识 0-正常 1-删除")
    private Boolean deleted;

    @ApiModelProperty("创建者")
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty("更新者")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty("备注")
    private String remark;

}
