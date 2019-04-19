package com.sample.handler;

import com.alibaba.dubbo.rpc.RpcException;
import com.sample.exception.ParamValidationException;
import com.sample.exception.PermissionDeniedException;
import com.sample.exception.ResourceNotFoundException;
import com.sample.util.JSONResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Auther: Wayne Zhang
 * @Date: 2019-03-21 09:36
 * @Description:
 */
@ControllerAdvice(annotations = ResponseBody.class)
@Slf4j
public class CustomExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public JSONResult commonExceptionHandle(Exception e) {
        log.error("[SystemException]Exception:", e);
        if (e instanceof RpcException) {
            return JSONResult.error("Dubbo调用服务异常:" + e.getMessage());
        }
        return JSONResult.error("System Error, please try again later! Message:" + e.getMessage());
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {ParamValidationException.class,
            ServletRequestBindingException.class})
    public JSONResult paramValidationExceptionHandle(Exception e) {
        log.error("[ParamValidationException]Exception:", e);
        return JSONResult.error(400, "Parameter validation failure! Message:" + e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public JSONResult resourceNotFoundExceptionHandle(Exception e) {
        log.error("[ResourceNotFoundException]Exception:", e);
        return JSONResult.error(404,"Resource not found! Message:" + e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = {PermissionDeniedException.class})
    public JSONResult permissionDeniedExceptionHandle(Exception e) {
        log.error("[PermissionDeniedException]Exception:", e);
        return JSONResult.error(401,"Permission Denied ! Message:" + e.getMessage());
    }
}
