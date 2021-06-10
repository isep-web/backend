package com.isep.project.exception.handler;

import cn.hutool.json.JSONUtil;
import com.isep.project.common.Status;
import com.isep.project.exception.JwtRuntimeException;
import com.isep.project.service.ResponseService;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Global exception handle
 *
 * @author : Xuan MIAO
 * @version : 2.0.0
 * @date : 2021/6/10
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler
{

    @ExceptionHandler(value = Exception.class)
    public void handlerException(HttpServletResponse response, Exception e) throws Exception
    {
        if (e instanceof NoHandlerFoundException)
        {
            log.error("[GlobalExceptionHandler]NoHandlerFoundException: Method: {}, Path: {}",
                    ((NoHandlerFoundException) e).getRequestURL(),
                    ((NoHandlerFoundException) e).getHttpMethod());
            ResponseService.renderJson(response, Status.REQUEST_NOT_FOUND,
                    ((NoHandlerFoundException) e).getRequestURL());
        } else if (e instanceof HttpRequestMethodNotSupportedException)
        {
            log.error("[GlobalExceptionHandler]HttpRequestMethodNotSupportedException: Method: {}, Action: {}",
                    ((HttpRequestMethodNotSupportedException) e).getMethod(), JSONUtil.toJsonStr(
                            ((HttpRequestMethodNotSupportedException) e)
                                    .getSupportedHttpMethods()));
            ResponseService.renderJson(response, Status.HTTP_BAD_METHOD, e.getMessage());
        } else if (e instanceof MethodArgumentNotValidException)
        {
            log.error("[GlobalExceptionHandler]MethodArgumentNotValidException", e);
            ResponseService.renderJson(response, Status.BAD_REQUEST, e.getMessage());
        } else if (e instanceof ConstraintViolationException)
        {
            log.error("[GlobalExceptionHandler]ConstraintViolationException", e);
            ResponseService.renderJson(response, Status.BAD_REQUEST, e.getMessage());
        } else if (e instanceof MethodArgumentTypeMismatchException)
        {
            log.error("[GlobalExceptionHandler]MethodArgumentTypeMismatchException: Param: {}, Message: {}",
                    ((MethodArgumentTypeMismatchException) e).getName(),
                    ((MethodArgumentTypeMismatchException) e).getMessage());
            ResponseService.renderJson(response, Status.PARAM_NOT_MATCH, e.getMessage());
        } else if (e instanceof HttpMessageNotReadableException)
        {
            log.error("[GlobalExceptionHandler]HttpMessageNotReadableException: Message: {}",
                    ((HttpMessageNotReadableException) e).getMessage());
            ResponseService.renderJson(response, Status.PARAM_NOT_NULL, e.getMessage());
        } else if (e instanceof BadCredentialsException)
        {
            log.error("[GlobalExceptionHandler]BadCredentialsException: Message: {}", e.getMessage());
            ResponseService.renderJson(response, Status.USERNAME_PASSWORD_ERROR, e.getMessage());
        } else if (e instanceof DisabledException)
        {
            log.error("[GlobalExceptionHandler]BadCredentialsException: Message: {}", e.getMessage());
            ResponseService.renderJson(response, Status.USER_DISABLED, e.getMessage());
        } else if (e instanceof JwtRuntimeException)
        {
            log.error("[GlobalExceptionHandler]DataManagerException: Code: {}, Message: {}",
                    ((JwtRuntimeException) e).getStatus().getStatus(), e.getMessage());
            ResponseService.renderJson(response,((JwtRuntimeException) e).getStatus(), e.getMessage());
        } else if (e instanceof ResourceNotFoundException)
        {
            log.error("[GlobalExceptionHandler]ResourceNotFoundException: Message: {}", e.getMessage());
            ResponseService.renderJson(response, Status.RESOURCE_NOT_FOUND, e.getMessage());
        } else
        {
            throw e;
//            log.error("[GlobalExceptionHandler]{}: Message: {} ", e.getClass(), e.getMessage());
//            ResponseService.renderJson(response, Status.ERROR, e.getMessage());
        }
    }
}
