package com.isep.project.payload;

import com.isep.project.common.Status;
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
}
