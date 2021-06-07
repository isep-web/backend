package com.isep.project.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 异常基类
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-07 14:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class BaseException extends RuntimeException
{

    private static final long serialVersionUID = -7435680600761438694L;
    private Integer code;
    private String message;
    private Object data;

    public BaseException(Status status)
    {
        super(status.getMessage());
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    public BaseException(Status status, Object data)
    {
        this(status);
        this.data = data;
    }

    public BaseException(Integer code, String message)
    {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BaseException(Integer code, String message, Object data)
    {
        this(code, message);
        this.data = data;
    }
}
