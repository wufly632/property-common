package cn.wufly.property.common.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

public class SnowflakeUtil {
    private static Snowflake snowflake;
    private static Long dataCenterId = 1L;
    private static Long workerId = 1L;

    public SnowflakeUtil() {
    }

    public static long nextId() {
        return snowflake.nextId();
    }

    public static String nextId(String bussinessPre) {
        return bussinessPre + snowflake.nextIdStr();
    }

    static {
        snowflake = IdUtil.getSnowflake(workerId, dataCenterId);
    }
}
