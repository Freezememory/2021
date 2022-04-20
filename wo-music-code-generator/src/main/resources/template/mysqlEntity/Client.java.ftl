import com.tydic.wo.music.core.api.req.LongIdReq;
import com.tydic.wo.music.core.api.rsp.OptionR;
import com.tydic.wo.music.core.api.rsp.PageR;
import com.tydic.wo.music.core.api.rsp.R;
import com.tydic.wo.music.core.security.annot.HasPermission;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* <p>
    * ${comment} 开放接口
    * </p>
*
* @author ${author}
* @since ${time}
*/
@FeignClient(${Ser}Service.NAME)
@RequestMapping("/${module}")
public interface ${Eo}Client {


@ApiOperation("查询")
@PostMapping("/get")
R<${Eo}> get(@RequestBody LongIdReq req);

@ApiOperation("分页查询")
@PostMapping("/page")
PageR<${Eo}> page(@RequestBody ${Eo}PageReq req);

@ApiOperation("选项列表")
@PostMapping("/option")
OptionR
<String> option();

    @ApiOperation("新增")
    @PostMapping("/add")
    R<${Eo}> add(@RequestBody ${Eo} req);

    @ApiOperation("更新")
    @PostMapping("/update")
    R update(@RequestBody ${Eo} req);

    @ApiOperation("删除")
    @PostMapping("/delete")
    R delete(@RequestBody LongIdReq req);
    }

