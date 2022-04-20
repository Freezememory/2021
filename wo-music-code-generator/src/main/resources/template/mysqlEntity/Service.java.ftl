import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tydic.wo.music.core.api.req.LongIdReq;
import com.tydic.wo.music.core.api.rsp.Option;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.tydic.wo.music.core.util.StrUtil.isNotBlank;
import static com.tydic.wo.music.core.util.ValidUtil.validateNotNull;
import static com.tydic.wo.music.core.util.ValidUtil.isNotNull;
import static com.tydic.wo.music.core.util.ValidUtil.validateTrue;

/**
* <p>
    * ${comment} service层
    * </p>
*
* @author ${author}
* @since ${time}
*/
@Service
public class ${Eo}Service extends ServiceImpl
<${Eo}Mapper, ${Eo}> {

public ${Eo} get(LongIdReq req) {
validateNotNull(req.getId(), "${comment} 主键不能为空");
return super.getById(req.getId());
}

public IPage<${Eo}> page(${Eo}PageReq req) {
return super.lambdaQuery()
<#list fields as field>
    ${field.condition}
</#list>
.page(req.page());
}

public List
<Option
<String>> option() {
    return super.lambdaQuery()
    .select(${Eo}::getId, ${Eo}::getRemark)
    .list()
    .stream()
    .map(${eo} -> new Option<>(${eo}.getId(), ${eo}.getRemark()))
    .collect(Collectors.toList());
    }

    public ${Eo} add(${Eo} req) {
    <#list fields as field>
        <#if field.required>
            ${field.validator}
        </#if>
    </#list>
    validateTrue(super.save(req), "${comment} 新增失败");
    return req;
    }

    public Boolean update(${Eo} req) {
    validateNotNull(req.getId(), "${comment} 主键不能为空");
    validateTrue(super.updateById(req), "${comment} 新增失败");
    return true;
    }

    public Boolean delete(LongIdReq req) {
    validateNotNull(req.getId(), "${comment} 主键不能为空");
    validateTrue(super.removeById(req.getId()), "${comment} 删除失败");
    return true;
    }
    }

