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
 * @Date: 2023/6/25 15:18
 * @Description :
 */
@ApiModel("标签类型信息")
@Data
public class LabelType {

    @TableId
    private Integer id;

    @ApiModelProperty("标签类型名称")
    private String labelTypeName;

    @ApiModelProperty("父级id 默认为0")
    private String parentId;

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
