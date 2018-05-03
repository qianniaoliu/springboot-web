package com.athena.px.springbootweb.controller;

import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description:
 * @Author: ShenLong
 * @CreateDate: 2018/4/21 15:55
 */
@Controller
@ImportResource
public class IndexController {

    @GetMapping(value = "/index",params = "para=1")
    public String index(){
//        throw new NullPointerException();
        return "index";
    }
}
