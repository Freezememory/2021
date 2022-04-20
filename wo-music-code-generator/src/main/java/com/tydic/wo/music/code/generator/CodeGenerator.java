package com.tydic.wo.music.code.generator;

import com.tydic.wo.music.core.util.ValidUtil;
import com.wanglj.exercise.entity.TerminalLog;
import com.wanglj.exercise.entity.UserLog;
import com.wanglj.exercise.entity.UserOperationLog;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;

@Slf4j
public class CodeGenerator {
    private static String mysqlEntityPath = "template/mysqlEntity";

    public static void main(String[] args) {
        CodeGeneratorInfo.builder()
                .author("Wanglj")
                .service("业务")
                .templatePath(mysqlEntityPath)
                .entityClasses(new Class[]{TerminalLog.class, UserLog.class, UserOperationLog.class})
                .modules(new String[]{"TerminalLog", "UserLog", "UserOperationLog"})
                .output("F:\\抖音视频\\code\\")
                .bindingMap(new LinkedHashMap<>())
                .build()
                .generate();

    }

    @Slf4j
    @Data
    @Builder
    public static class CodeGeneratorInfo {
        /**
         * 作者
         */
        private String author;
        /**
         * 所属服务
         */
        private String service;
        /**
         * 所属模块
         */
        private String[] modules;
        /**
         * 模板路径
         */
        private String templatePath;
        /**
         * 实体类
         */
        private Class[] entityClasses;
        /**
         * 输出路径
         */
        private String output;
        /**
         * 绑定数据
         */
        private LinkedHashMap<String, Object> bindingMap;

        public void generate() {
            ValidUtil.validateNotBlank(this.author, "author 不能为空");
            ValidUtil.validateNotBlank(this.service, "service 不能为空");
            ValidUtil.validateNotNull(this.modules, "module 不能为空");
            ValidUtil.validateNotBlank(this.templatePath, "templatePath 不能为空");
            ValidUtil.validateNotNull(this.entityClasses, "entityClass 不能为空");
            ValidUtil.validateNotBlank(this.output, "output 不能为空");
            if (this.bindingMap == null) {
                this.bindingMap = new LinkedHashMap<>();
            }
            CodeGenerateUtil.generate(this);
        }
    }

}
