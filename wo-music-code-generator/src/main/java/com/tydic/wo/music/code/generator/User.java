package com.tydic.wo.music.code.generator;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Wanglj
 * @Date: 2021/12/1 15:18
 * @Description :
 */
@ApiModel("用户信息")
@Data
public class User {

    @TableId
    private Integer id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("性别")
    private String sex;

}
