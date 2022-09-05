package cn.wufly.property.common.model;

import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class BaseDOWithExt extends BaseDO{

    @ApiModelProperty(value = "删除时间")
    @TableLogic
    Integer isDeleted;

    @ApiModelProperty(value = "创建人")
    String createUser;

    @Override
    public void initBaseField() {
        this.setIsDeleted(0);
        this.setCreateUser("");
        super.initBaseField();
    }
}
