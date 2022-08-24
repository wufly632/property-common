package cn.wufly.property.common.bo;

import cn.wufly.property.common.enums.ConverterEnum;
import lombok.Data;

@Data
public class FieldMapBO {

    /**
     * 字段名
     */
    private String fieldName;

    /**
     * 目标类字段名
     */
    private String targetName;

    /**
     * 转换枚举
     */
    private ConverterEnum converter;


}
