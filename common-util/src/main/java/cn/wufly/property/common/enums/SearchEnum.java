package cn.wufly.property.common.enums;

/**
 * @author Wufly  fei.wu@transsnet.com
 * @project: property-common
 * @description:
 * @date 2022/8/24
 */
public interface SearchEnum<T, V> {

    T getCode();

    default V getType() {
        return (V) this.getClass();
    }


}
