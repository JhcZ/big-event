package com.jhc.exception;

import com.jhc.pojo.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @description:处理所有异常的类
 * @author: JhcZ
 * @Email：2325947239@qq.com
 * @create: 2024-01-04 18:34
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result handleException(Exception e){
        e.printStackTrace();
        return Result.error(StringUtils.hasLength(e.getMessage()) ? e.getMessage() : "操作错误！");
    }
}