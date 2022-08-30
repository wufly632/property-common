package cn.wufly.property.common.model.exception;

public enum SystemErrorEnum implements BizExceptionEnumI {
    /**
     * 系统异常在网关层处理国际化
     */
    /**
     * 系统处理失败
     */
    SUCCESS(0, "Success"),
    /**
     * 系统处理失败
     */
    SYSTEM_ERROR(100, "SYSTEM_ERROR"),
    /**
     * 接口请求超时
     */
    INTERFACE_REQUEST_TIMEOUT(1002, "INTERFACE_REQUEST_TIMEOUT"),
    /**
     * 参数校验失败
     */
    PARAMETERS_CALIBRATION_FAILURE(1003, "PARAMETERS_CALIBRATION_FAILURE"),
    /**
     * 数据库操作失败
     */
    DATABASE_OPERATION_FAILURE(1004, "DATABASE_OPERATION_FAILURE"),
    /**
     * Redis缓存操作失败
     */
    REDIS_CACHE_FAILS(1005, "REDIS_CACHE_FAILS"),
    /**
     * 远程服务超时
     */
    REMOTE_SERVICE_TIMEOUT(1006, "REMOTE_SERVICE_TIMEOUT"),
    /**
     * 远程服务异常
     */
    REMOTE_SERVICE_EXCEPTION(1007, "REMOTE_SERVICE_EXCEPTION"),

    /**
     * 未知城市
     */
    UNKNOWN_CURRENCY(1008, "UNKNOWN_CURRENCY"),

    /**
     * mq异常
     */
    MQ_EXCEPTION(1009, "MQ_EXCEPTION"),


    /**
     * 参数异常
     */
    ARGS_ERROR(1010,"args error"),

    ;

    final Integer code;
    final String desc;

    SystemErrorEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

}

