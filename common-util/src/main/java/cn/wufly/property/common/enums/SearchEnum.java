package cn.wufly.property.common.enums;

public interface SearchEnum<T, V> {

    T getCode();

    default V getType() {
        return (V) this.getClass();
    }


}
