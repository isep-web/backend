package com.isep.project.exception;

import com.isep.project.common.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Runtime exceptions thrown by jwt
 *
 * @author : Xuan MIAO
 * @version : 2.0.0
 * @date : 2021/6/10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class JwtRuntimeException extends RuntimeException
{

    private static final long serialVersionUID = -5135378589589126710L;

    private Status status;
    private Object data;

    public JwtRuntimeException(Status status)
    {
        super(status.getMessage());
        this.status = status;
    }

    public JwtRuntimeException(Status status, Object data)
    {
        super(status.getMessage());
        this.status = status;
        this.data = data;
    }
}
