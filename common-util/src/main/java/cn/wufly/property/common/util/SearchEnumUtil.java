package cn.wufly.property.common.util;

import cn.wufly.property.common.enums.SearchEnum;

import java.util.Objects;

public class SearchEnumUtil {

    /**
     * 通过code寻找枚举
     * @param seCLass 实现了SearchEnum的枚举
     * @param code 目标code
     * @return
     */
    public static <T, V> V findByCode(T code, Class<? extends SearchEnum<T, V>> seCLass) {
        if (seCLass == null || !seCLass.isEnum()) {
            return null;
        }
        return (V) findByArray(seCLass.getEnumConstants(), code);
    }

    /**
     * 兼容 orika 动态获取情况，逻辑同 {@linkplain SearchEnumUtil findByCode}
     * @param clz
     * @param code
     * @return
     */
    public static SearchEnum findByClass(Class clz, Object code) {
        if (clz == null || !clz.isEnum()) {
            return null;
        }
        try {
            return findByArray((SearchEnum[]) clz.getEnumConstants(), code);
        } catch (Exception ex) {
            //Do nothing
        }
        return null;
    }

    private static SearchEnum findByArray(SearchEnum[] enumConstants, Object code) {
        for (SearchEnum se : enumConstants) {
            if (Objects.equals(se.getCode(),code)) {
                return se;
            }
        }
        return null;
    }
}

