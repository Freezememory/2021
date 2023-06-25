import com.tydic.wo.music.core.api.req.LongIdReq;
import com.tydic.wo.music.core.api.rsp.OptionR;
import com.tydic.wo.music.core.api.rsp.PageR;
import com.tydic.wo.music.core.api.rsp.R;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

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
@RequestMapping("/${module}")
@RequiredArgsConstructor
public class ${Eo}Controller {

private final ${Eo}Service ${eo}Service;

@ApiOperation("查询")
@PostMapping("/get")
public R<${Eo}> get(@RequestBody LongIdReq req) {
return R.ok(${eo}Service.get(req));
}

@ApiOperation("分页查询")
@PostMapping("/page")
public PageR<${Eo}> page(@RequestBody ${Eo}PageReq req) {
return PageR.ok(${eo}Service.page(req));
}

@ApiOperation("新增")
@PostMapping("/add")
public R<${Eo}> add(@RequestBody ${Eo} req) {
return R.ok(${eo}Service.add(req));
}

@ApiOperation("更新")
@PostMapping("/update")
public R update(@RequestBody ${Eo} req) {
return R.ok(${eo}Service.update(req));
}

@ApiOperation("删除")
@PostMapping("/delete")
public R delete(@RequestBody LongIdReq req) {
return R.ok(${eo}Service.delete(req));
}

}
