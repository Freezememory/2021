import com.tydic.wo.music.core.api.req.PageReq;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
* <p>
    * ${comment} 分页查询条件
    * </p>
*
* @author ${author}
* @since ${time}
*/
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ${Eo}PageReq extends PageReq {
<#list fields as field>

    @ApiModelProperty("${field.comment}")
    private ${field.upType} ${field.property};
</#list>
}
