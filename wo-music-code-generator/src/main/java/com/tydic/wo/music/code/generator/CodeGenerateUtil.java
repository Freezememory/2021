package com.tydic.wo.music.code.generator;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.tydic.wo.music.core.util.FileUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class CodeGenerateUtil {
    private static Class entityClass = null;

    public static void generate(CodeGenerator.CodeGeneratorInfo info) {


        for (int i = 0; i < info.getEntityClasses().length; i++) {
            LinkedHashMap<String, Object> bindingMap = info.getBindingMap();
            entityClass = info.getEntityClasses()[i];
            bindingMap.put("author", info.getAuthor());
            bindingMap.put("time", new DateTime().toString());
            bindingMap.put("ser", info.getService());
            bindingMap.put("Ser", StrUtil.upperFirst(info.getService()));
            bindingMap.put("SER", info.getService().toUpperCase());
            bindingMap.put("module", info.getModules()[i]);
            bindingMap.put("MODULE", info.getModules()[i].toUpperCase());
            bindingMap.put("eo", StrUtil.lowerFirst(entityClass.getSimpleName()));
            bindingMap.put("Eo", entityClass.getSimpleName());
            bindingMap.put("comment", getComment(entityClass));
            bindingMap.put("columns", getColumns(entityClass));
            bindingMap.put("table_name", getTableName(entityClass));
            bindingMap.put("id_field", getIdField(entityClass));
            bindingMap.put("has_id", getIdField(entityClass) != null);
            bindingMap.put("super_fields", getFields(entityClass.getSuperclass()));
            bindingMap.put("fields", getFields(entityClass));

            TemplateEngine engine = TemplateUtil.createEngine(
                    new TemplateConfig(info.getTemplatePath(), TemplateConfig.ResourceMode.CLASSPATH));

            for (String templateName : getTemplates(info.getTemplatePath())) {
                Template template = engine.getTemplate(templateName);
                File outPutFile = getOutputFile(templateName, info.getOutput(), entityClass);
                template.render(bindingMap, outPutFile);
                log.info("{} 已创建", outPutFile.getName());
            }
        }

    }

    private static String getColumns(Class entityClass) {
        Field idField = getIdField(entityClass);
        List<Field> superFields = getFields(entityClass.getSuperclass());
        List<Field> fields = getFields(entityClass);

        ArrayList<Field> list = CollUtil.newArrayList(idField);
        list.addAll(superFields);
        list.addAll(fields);
        return list.stream()
                .filter(ObjectUtil::isNotNull)
                .map(Field::getColumn)
                .collect(Collectors.joining(",\n\t\t"));
    }

    private static List<Field> getFields(Class entityClass) {
        TableInfo tableInfo = TableInfoHelper.initTableInfo(null, entityClass);
        List<TableFieldInfo> allFieldList = tableInfo.getFieldList();
        List<String> superProperties = TableInfoHelper.initTableInfo(null, entityClass.getSuperclass())
                .getFieldList()
                .stream()
                .map(x -> x.getProperty())
                .collect(Collectors.toList());
        return allFieldList.stream()
                .filter(x -> !superProperties.contains(x.getProperty()))
                .map(x -> getField(x))
                .collect(Collectors.toList());
    }

    private static Field getField(TableFieldInfo tableFieldInfo) {
        java.lang.reflect.Field reflectField = ReflectUtil.getField(entityClass, tableFieldInfo.getProperty());
        Field field = Field.builder()
                .Eo(entityClass.getSimpleName())
                .comment(AnnotationUtil.getAnnotationValue(reflectField, ApiModelProperty.class))
                .column(tableFieldInfo.getColumn())
                .property(tableFieldInfo.getProperty())
                .upProperty(StrUtil.upperFirst(tableFieldInfo.getProperty()))
                .required(AnnotationUtil.getAnnotationValue(reflectField, ApiModelProperty.class, "required"))
                .upType(tableFieldInfo.getPropertyType().getSimpleName())
                .type(StrUtil.lowerFirst(tableFieldInfo.getPropertyType().getSimpleName()))
                .build();
        field.initCondition();
        field.getValidator();
        log.info(field.toString());
        return field;
    }

    private static File getOutputFile(String templateName, String output, Class entityClass) {
        return cn.hutool.core.io.FileUtil.newFile(
                output
                        + entityClass.getSimpleName()
                        + StrUtil.removeSuffix(templateName, ".ftl")
        );
    }

    private static List<String> getTemplates(String templatePath) {
        return Stream.of(FileUtil.ls(templatePath))
                .filter(file -> !file.isDirectory())
                .filter(file -> file.getName().endsWith(".ftl"))
                .map(File::getName)
                .collect(Collectors.toList());

    }

    private static Field getIdField(Class entityClass) {
        TableInfo tableInfo = TableInfoHelper.initTableInfo(null, entityClass);
        String keyProperty = tableInfo.getKeyProperty();
        if (StrUtil.isBlank(keyProperty)) {
            return null;
        }
        java.lang.reflect.Field reflectField = ReflectUtil.getField(entityClass, tableInfo.getKeyProperty());
        Field field = Field.builder()
                .Eo(entityClass.getSimpleName())
                .comment(AnnotationUtil.getAnnotationValue(reflectField, ApiModelProperty.class))
                .column(tableInfo.getKeyColumn())
                .property(tableInfo.getKeyProperty())
                .upProperty(StrUtil.upperFirst(tableInfo.getKeyProperty()))
                .required(AnnotationUtil.getAnnotationValue(reflectField, ApiModelProperty.class, "required"))
                .upType(tableInfo.getKeyType().getSimpleName())
                .type(StrUtil.lowerFirst(tableInfo.getKeyType().getSimpleName()))
                .build();
        field.initCondition();
        field.getValidator();
        log.info(field.toString());
        return field;
    }

    private static String getComment(Class entityClass) {
        return AnnotationUtil.getAnnotationValue(entityClass, ApiModel.class);
    }

    private static String getTableName(Class entityClass) {
        return TableInfoHelper.initTableInfo(null, entityClass).getTableName();
    }
}
