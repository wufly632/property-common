package cn.wufly.property.common.model.response;

import cn.wufly.property.common.model.exception.SystemErrorEnum;
import lombok.Data;
import org.slf4j.MDC;

@Data
public class BaseHttpResult<T> {
    /**
     * 信息
     */
    private String message;
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 成功
     */
    private Boolean success;
    /**
     * 结果
     */
    private T data;
    /**
     * 请求id
     */
    private String requestId;
    public static <T> BaseHttpResult<T> success(T t) {
        return of(t, SystemErrorEnum.SUCCESS);
    }

    public static <T> BaseHttpResult<T> success() {
        return of(null, SystemErrorEnum.SUCCESS);
    }


    public static <T> BaseHttpResult<T> fail(SystemErrorEnum exceptionEnum) {
        return of(null, exceptionEnum);
    }

    public static <T> BaseHttpResult<T> fail(Integer code, String message) {
        BaseHttpResult<T> baseHttpResult = new BaseHttpResult<>();
        baseHttpResult.setSuccess(false);
        baseHttpResult.setMessage(message);
        baseHttpResult.setCode(code);
        baseHttpResult.setRequestId(MDC.get("traceId"));
        return baseHttpResult;
    }


    private static <T> BaseHttpResult<T> of(T t, SystemErrorEnum exceptionEnum) {
        BaseHttpResult<T> baseHttpResult = new BaseHttpResult<>();
        baseHttpResult.setSuccess(SystemErrorEnum.SUCCESS.equals(exceptionEnum));
        baseHttpResult.setCode(exceptionEnum.getCode());
        baseHttpResult.setMessage(exceptionEnum.getDesc());
        baseHttpResult.setData(t);
        baseHttpResult.setRequestId(MDC.get("traceId"));
        return baseHttpResult;
    }
}

