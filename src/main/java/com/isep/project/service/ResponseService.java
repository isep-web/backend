package com.isep.project.service;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.isep.project.common.Status;
import com.isep.project.exception.JwtRuntimeException;
import com.isep.project.payload.ApiResponse;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * Response generate service
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/5/31
 */
@Slf4j
public class ResponseService
{

    /**
     * Generate response
     *
     * @param response response
     * @param status   {@link Status} status
     * @param data     data
     */

    public static void renderJson(HttpServletResponse response, Status status, Object data)
    {
        try
        {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "*");
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(status.getStatus());
            response.getWriter().write(JSONUtil
                    .toJsonStr(new JSONObject(new ApiResponse(status, data), false)));
        } catch (IOException e)
        {
            log.error("ResponseService IO Exception: ", e);
        }
    }
}
