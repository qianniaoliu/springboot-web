package com.athena.px.controller;

import com.athena.px.model.Gg;
import com.athena.px.service.GgService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 * @Author: ShenLong
 * @CreateDate: 2018/5/15 9:34
 */
@Controller
public class IndexController {

    final GgService ggService;

    public IndexController(GgService ggService) {
        this.ggService = ggService;
    }


    @GetMapping("/index")
    public String index(){
        return "/index";
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public Gg getGg(@PathVariable Integer id){
        return ggService.findGg(id);
    }
}
