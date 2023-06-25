<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${namespace}Mapper">

    <!-- 通用查询映射结果 -->
    <resultMap id="resultMap" type="${namespace}">
        <#if has_id>
            <id column="${id_field.column}" property="${id_field.property}"/>
        </#if>
        <#list super_fields as field>
            <result column="${field.column}" property="${field.property}"/>
        </#list>
        <#list fields as field>
            <result column="${field.column}" property="${field.property}"/>
        </#list>
    </resultMap>

    <!-- 通用查询结果列 -->
    <sql id="columns">
        ${columns}
    </sql>

</mapper>
