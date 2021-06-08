package com.isep.project.common;

import com.isep.project.exception.SecurityRuntimeException;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Api response structure
 *
 * @author : Xuan MIAO
 * @version : 2.0.0
 * @date : 2021/6/5
 */
@Data
@AllArgsConstructor
public class ApiResponse implements Serializable
{

    private static final long serialVersionUID = -6438230385162744637L;

    /**
     * Status Code
     */
    private Integer status;

    private String message;

    private Object data;

    /**
     * Build Api response with {@link Status}
     *
     * @param status status {@link Status}
     * @param data   data
     */
    public ApiResponse(Status status, Object data)
    {
        this.status = status.getStatus();
        this.message = status.getMessage();
        this.data = data;
    }

    /**
     * Build Api response with subclass of {@link SecurityRuntimeException}
     *
     * @param t   exception
     * @param <T> subclass of {@link SecurityRuntimeException}
     */
    public <T extends SecurityRuntimeException> ApiResponse(T t)
    {
        this.status = t.getStatus();
        this.message = t.getMessage();
        this.data = t.getData();
    }
}
