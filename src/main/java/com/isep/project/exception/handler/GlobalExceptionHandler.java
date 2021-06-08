//package com.isep.project.exception.handler;
//
//import cn.hutool.core.collection.CollUtil;
//import cn.hutool.json.JSONUtil;
//import com.isep.project.common.ApiResponse;
//import com.isep.project.common.Status;
//import com.isep.project.exception.SecurityRuntimeException;
//import javax.validation.ConstraintViolationException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.DisabledException;
//import org.springframework.web.HttpRequestMethodNotSupportedException;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
//import org.springframework.web.servlet.NoHandlerFoundException;
//
///**
// * <p>
// * 全局统一异常处理
// * </p>
// *
// * @author yangkai.shen
// * @date Created in 2018-12-10 17:00
// */
//@ControllerAdvice
//@Slf4j
//public class GlobalExceptionHandler
//{
//
//    @ExceptionHandler(value = Exception.class)
//    @ResponseBody
//    public ApiResponse handlerException(Exception e)
//    {
//        if (e instanceof NoHandlerFoundException)
//        {
//            log.error("【全局异常拦截】NoHandlerFoundException: 请求方法 {}, 请求路径 {}",
//                    ((NoHandlerFoundException) e).getRequestURL(),
//                    ((NoHandlerFoundException) e).getHttpMethod());
//            return new ApiResponse(Status.REQUEST_NOT_FOUND, null);
//        } else if (e instanceof HttpRequestMethodNotSupportedException)
//        {
//            log.error("【全局异常拦截】HttpRequestMethodNotSupportedException: 当前请求方式 {}, 支持请求方式 {}",
//                    ((HttpRequestMethodNotSupportedException) e).getMethod(), JSONUtil.toJsonStr(
//                            ((HttpRequestMethodNotSupportedException) e)
//                                    .getSupportedHttpMethods()));
//            return new ApiResponse(Status.HTTP_BAD_METHOD, null);
//        } else if (e instanceof MethodArgumentNotValidException)
//        {
//            log.error("【全局异常拦截】MethodArgumentNotValidException", e);
//            return new ApiResponse(Status.BAD_REQUEST.getStatus(),
//                    ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors().get(0)
//                            .getDefaultMessage(), null);
//        } else if (e instanceof ConstraintViolationException)
//        {
//            log.error("【全局异常拦截】ConstraintViolationException", e);
//            return new ApiResponse(Status.BAD_REQUEST.getStatus(),
//                    CollUtil.getFirst(((ConstraintViolationException) e).getConstraintViolations())
//                            .getMessage(), null);
//        } else if (e instanceof MethodArgumentTypeMismatchException)
//        {
//            log.error("【全局异常拦截】MethodArgumentTypeMismatchException: 参数名 {}, 异常信息 {}",
//                    ((MethodArgumentTypeMismatchException) e).getName(),
//                    ((MethodArgumentTypeMismatchException) e).getMessage());
//            return new ApiResponse(Status.PARAM_NOT_MATCH, null);
//        }
////        else if (e instanceof HttpMessageNotReadableException)
////        {
////            log.error("【全局异常拦截】HttpMessageNotReadableException: 错误信息 {}",
////                    ((HttpMessageNotReadableException) e).getMessage());
////            return new ApiResponse(Status.PARAM_NOT_NULL, null);
////        }
//        else if (e instanceof BadCredentialsException)
//        {
//            log.error("【全局异常拦截】BadCredentialsException: 错误信息 {}", e.getMessage());
//            return new ApiResponse(Status.USERNAME_PASSWORD_ERROR, null);
//        } else if (e instanceof DisabledException)
//        {
//            log.error("【全局异常拦截】BadCredentialsException: 错误信息 {}", e.getMessage());
//            return new ApiResponse(Status.USER_DISABLED, null);
//        } else if (e instanceof SecurityRuntimeException)
//        {
//            log.error("【全局异常拦截】DataManagerException: 状态码 {}, 异常信息 {}",
//                    ((SecurityRuntimeException) e).getStatus(), e.getMessage());
//            return new ApiResponse((SecurityRuntimeException) e);
//        }
//
//        log.error("【全局异常拦截】: 异常信息 {} ", e.getMessage());
//        return new ApiResponse(Status.ERROR, null);
//    }
//}
