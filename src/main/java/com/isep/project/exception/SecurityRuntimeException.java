package com.isep.project.exception;

import com.isep.project.common.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Super class of exceptions
 *
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/6/5
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class SecurityRuntimeException extends RuntimeException
{

    private static final long serialVersionUID = 7629926439985404963L;

    private Integer status;
    private String message;
    private Object data;

    public SecurityRuntimeException(Status status)
    {
        super(status.getMessage());
        this.status = status.getStatus();
        this.message = status.getMessage();
    }

    public SecurityRuntimeException(Status status, Object data)
    {
        this(status);
        this.data = data;
    }

    public SecurityRuntimeException(Integer status, String message)
    {
        super(message);
        this.status = status;
        this.message = message;
    }

    public SecurityRuntimeException(Integer status, String message, Object data)
    {
        this(status, message);
        this.data = data;
    }
}
