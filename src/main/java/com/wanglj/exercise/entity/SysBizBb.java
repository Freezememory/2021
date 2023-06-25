package com.wanglj.exercise.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 报表信息表 sys_biz_bb
 * @author Wanglj
 * @Date 2022-12-06 14:00:01
 */

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_biz_bb")
@ApiModel(value = "报表信息表")
public class SysBizBb extends Model<SysBizBb> {
    //private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户标识")
    @TableId
    private Long id;

    /**
     * 一级分类
     */
    @ApiModelProperty("一级分类")
    private String firstKind;


    /**
     * 二级分类
     */
    @ApiModelProperty("二级分类")
    private Integer secondKind;

    /**
     * 统计时间
     */
    @ApiModelProperty("统计时间")

    private String statisDate;

    /**
     * 归属县区ID
     */
    @ApiModelProperty("归属县区ID")

    private String saleId;

    /**
     * 归属县区
     */
    @ApiModelProperty("归属县区")

    private String saleName;

    /**
     * 归属网格ID
     */
    @ApiModelProperty("归属网格ID")

    private String gridId;

    /**
     * 归属微网格
     */
    @ApiModelProperty("归属微网格")

    private String gridName;

    /**
     * 归属微网格ID
     */
    @ApiModelProperty("归属微网格ID")

    private String regionId;

    /**
     * 归属微网格
     */
    @ApiModelProperty("归属微网格")

    private String regionName;


    /**
     * 人员姓名
     */
    @ApiModelProperty("人员姓名")

    private String operatorName;


    /**
     * 办理人手机号
     */
    @ApiModelProperty("办理人手机号")

    private String phone;

    /**
     * 部门ID
     */
    @ApiModelProperty("部门ID")

    private String deptId;

    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")

    private String userId;

    /**
     * 人员工号
     */
    @ApiModelProperty("人员工号")

    private String bossCode;

    /**
     * 拓展字段1
     */
    @ApiModelProperty("拓展字段1")

    private String col1;

    /**
     * 拓展字段2
     */
    @ApiModelProperty("拓展字段2")

    private String col2;

    /**
     * 拓展字段3
     */
    @ApiModelProperty("拓展字段3")

    private String col3;


    /**
     * 拓展字段1
     */
    @ApiModelProperty("拓展字段4")

    private String col4;

    /**
     * 拓展字段2
     */
    @ApiModelProperty("拓展字段5")

    private String col5;

    /**
     * 拓展字段3
     */
    @ApiModelProperty("拓展字段6")

    private String col6;

    /**
     * 拓展字段1
     */
    @ApiModelProperty("拓展字段7")

    private String col7;

    /**
     * 拓展字段2
     */
    @ApiModelProperty("拓展字段8")

    private String col8;

    /**
     * 拓展字段3
     */
    @ApiModelProperty("拓展字段9")

    private String col9;
    /**
     * 拓展字段1
     */
    @ApiModelProperty("拓展字段10")
    private String col10;

}
