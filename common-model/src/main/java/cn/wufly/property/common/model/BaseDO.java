package cn.wufly.property.common.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseDO implements Serializable {
    private static final long serialVersionUID = -5716655385763521460L;
    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "创建时间")
    @TableField("created_at")
    private Date createdAt;

    @ApiModelProperty(value = "修改时间")
    @TableField("updated_at")
    private Date updatedAt;

    public void initBaseField() {
        Date date = new Date();
        createdAt = date;
        updatedAt = date;
    }

}
