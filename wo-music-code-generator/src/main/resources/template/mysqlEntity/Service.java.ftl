import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tydic.wo.music.core.api.req.LongIdReq;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* ${comment} 接口层
* @author ${author}
* @since ${time}
*/
public interface ${Eo}Service extends IService<${Eo}> {

${Eo} get(LongIdReq req);

IPage<${Eo}> page(${Eo}PageReq req);

${Eo} add(${Eo} req);

Boolean update(${Eo} req);

Boolean delete(LongIdReq req);

}

