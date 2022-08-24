package cn.wufly.property.common.anno;

import cn.wufly.property.common.enums.ConverterEnum;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface OrikaField {

    /**
     * 目标字段名
     * @return
     */
    String targetName();

    /**
     * 自定义转换规则
     * @return
     */
    ConverterEnum converter() default ConverterEnum.NONE;
}

