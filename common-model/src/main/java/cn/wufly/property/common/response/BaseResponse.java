package cn.wufly.property.common.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseResponse implements Serializable {
    private static final long serialVersionUID = 4552216107921279874L;
    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 请求id
     */
    private String requestId;
    /**
     * 返回时间戳
     */
    private Long timestamp;
    /**
     * 请求耗时
     */
    private Long elapsedTime;
    /**
     * 错误编码
     */
    private Long responseCode;
    /**
     * 消息提示
     */
    private String message;
    /**
     * 异常堆栈
     */
    private String exceptionStack;
    /**
     * 总记录数
     */
    private Integer totalRecordSize;
    /**
     * 是否有下一页
     */
    private boolean hasNext;
    /**
     * 每页大小
     */
    private Integer pageSize;
    /**
     * 当前页码
     */
    private Integer pageIndex;
    /**
     * 状态成功返回的业务对象
     */
    private Object entry;
}

