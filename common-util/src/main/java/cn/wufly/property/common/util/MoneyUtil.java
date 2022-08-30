package cn.wufly.property.common.util;

import java.math.BigDecimal;
import java.util.Optional;

public class MoneyUtil {

    public static final BigDecimal HUN = new BigDecimal("100");
    public static String transferToDecimal(Long longValue){
        return Optional.ofNullable(longValue).map(BigDecimal::new).map(x->x.divide(HUN,2,BigDecimal.ROUND_DOWN)).map(BigDecimal::stripTrailingZeros).map(BigDecimal::toPlainString).orElse("0");

    }

    public static Long transferToLong(BigDecimal bigDecimal){
        if (null == bigDecimal) {
            return 0L;
        }
        return bigDecimal.multiply(HUN).longValue();
    }

    public static String transferToString(BigDecimal bigDecimal){
        return Optional.ofNullable(bigDecimal).map(x->x.divide(HUN,2,BigDecimal.ROUND_DOWN)).map(BigDecimal::stripTrailingZeros).map(BigDecimal::toPlainString).orElse("0");

    }
}