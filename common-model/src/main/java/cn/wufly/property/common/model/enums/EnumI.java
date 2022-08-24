package cn.wufly.property.common.model.enums;

public interface EnumI<T> {
    /**
     * 枚举编码
     * @return 编码
     */
    T getCode();

    /**
     * 枚举描述
     * @return 描述
     */
    String getDesc();
}
