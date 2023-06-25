import com.tydic.wo.music.core.api.req.LongIdReq;
import com.tydic.wo.music.core.api.rsp.OptionR;
import com.tydic.wo.music.core.api.rsp.PageR;
import com.tydic.wo.music.core.api.rsp.R;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
* <p>
    * ${comment} controller层
    * </p>
*
* @author ${author}
* @since ${time}
*/
@Slf4j
@Api(tags = "${comment}")
@RestController
public class ${Eo}Controller implements ${Eo}Client {

@Autowired
private ${Eo}Service ${eo}Service;

@HasPermission(${Ser}ServicePerm.${MODULE} + "get")
@Override
public R<${Eo}> get(@RequestBody LongIdReq req) {
return R.ok(${eo}Service.get(req));
}

@HasPermission(${Ser}ServicePerm.${MODULE} + "get")
@Override
public PageR<${Eo}> page(@RequestBody ${Eo}PageReq req) {
return PageR.ok(${eo}Service.page(req));
}

@HasPermission(${Ser}ServicePerm.${MODULE} + "get")
@Override
public OptionR
<String> option() {
    return OptionR.ok(${eo}Service.option());
    }

    @HasPermission(${Ser}ServicePerm.${MODULE} + "add")
    @Override
    public R<${Eo}> add(@RequestBody ${Eo} req) {
    return R.ok(${eo}Service.add(req));
    }

    @HasPermission(${Ser}ServicePerm.${MODULE} + "update")
    @Override
    public R update(@RequestBody ${Eo} req) {
    return R.ok(${eo}Service.update(req));
    }

    @HasPermission(${Ser}ServicePerm.${MODULE} + "delete")
    @Override
    public R delete(@RequestBody LongIdReq req) {
    return R.ok(${eo}Service.delete(req));
    }
    }
