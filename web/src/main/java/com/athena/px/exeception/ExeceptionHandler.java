package com.athena.px.exeception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Description:
 * @Author: ShenLong
 * @CreateDate: 2018/5/24 14:47
 */
@ControllerAdvice(basePackages = "com.athena.px.controller")
public class ExeceptionHandler {

    @ExceptionHandler(value = {NullPointerException.class})
    public String pageNotFound(){
        return "/error/404";
    }
}
