package cn.wufly.property.common.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BaseRequest {

    String userId;

    @ApiModelProperty("用户类型 1-物业 2-业主 3-主任")
    Integer userType;
}
