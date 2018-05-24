package com.athena.px.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description:
 * @Author: ShenLong
 * @CreateDate: 2018/5/24 9:33
 */
@Controller
public class LoginController {


    @GetMapping(value = "/error/notuser")
    public String error(){
        return "/error/404";
    }
}
