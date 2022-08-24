package cn.wufly.property.common.model.exception;

import lombok.Data;

import java.util.Arrays;

@Data
public class BizException extends RuntimeException {
    private static final long serialVersionUID = 2742081965316171577L;
    /**
     * 错误码
     */
    private String errorCode;
    /**
     * 错误码
     */
    private Object[] args;

    /**
     * 错误信息
     */
    private String errorMsg;

    public BizException(BizExceptionEnumI bizExceptionEnumI, Object... args) {
        super("[" + bizExceptionEnumI.getCode() + "][" + bizExceptionEnumI.getDesc() + "]:" + Arrays.toString(args));
        this.errorCode = bizExceptionEnumI.getCode();
        this.errorMsg = bizExceptionEnumI.getDesc();
        this.args = args;
    }

    public BizException(String message, BizExceptionEnumI bizExceptionEnumI, Object... args) {
        super(message + ":" + Arrays.toString(args));
        this.errorCode = bizExceptionEnumI.getCode();
        this.errorMsg = bizExceptionEnumI.getDesc();
        this.args = args;
    }

    public BizException(String message, Throwable cause, BizExceptionEnumI bizExceptionEnumI, Object... args) {
        super(message + ":" + Arrays.toString(args) , cause);
        this.errorCode = bizExceptionEnumI.getCode();
        this.errorMsg = bizExceptionEnumI.getDesc();
        this.args = args;
    }

    public BizException(Throwable cause, BizExceptionEnumI bizExceptionEnumI, Object... args) {
        super(cause);
        this.errorCode = bizExceptionEnumI.getCode();
        this.errorMsg = bizExceptionEnumI.getDesc();
        this.args = args;
    }

    public BizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, BizExceptionEnumI bizExceptionEnumI, Object... args) {
        super(message + ":" + Arrays.toString(args), cause, enableSuppression, writableStackTrace);
        this.errorCode = bizExceptionEnumI.getCode();
        this.errorMsg = bizExceptionEnumI.getDesc();
        this.args = args;
    }

    public BizException(String errorCode, String errorMsg) {
        super("[" + errorCode + "][" + errorMsg + "]");
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}

