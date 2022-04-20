package com.tydic.wo.music.code.generator;

import cn.hutool.core.util.StrUtil;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Field {

    private String Eo;
    private String comment;
    private String column;
    private String property;
    private String upProperty;
    private Boolean required;
    private String type;
    private String upType;
    private String condition;
    private String validator;

    public void initCondition() {
        switch (this.upType) {
            case "String":
                this.condition = StrUtil.format(".like(isNotBlank(req.get{}()), {}::get{}, req.get{}())",
                        this.upProperty, Eo, this.upProperty, this.upProperty);
                break;
            case "List":
            case "Set":
                this.condition = StrUtil.format(".in(isNotEmpty(req.get{}()), {}::get{}, req.get{}())",
                        this.upProperty, Eo, this.upProperty, this.upProperty);
                break;
            default:
                this.condition = StrUtil.format(".eq(isNotNull(req.get{}()), {}::get{}, req.get{}())",
                        this.upProperty, Eo, this.upProperty, this.upProperty);
        }
    }

    public void initValidator() {
        /*if (typeSimpleName.equals("List")
                || typeSimpleName.equals("Set")) {
            validator = StrUtil.format(
                    "CollValidator.notEmpty(entity.getId(), \"{}不能为空\");",
                    comment
            );
        } else {
            validator = StrUtil.format(
                    "ValidUtil.validateNotEmpty(entity.get{}(), \"{}不能为空\");",
                    upProperty,
                    comment
            );
        }*/
    }
}
