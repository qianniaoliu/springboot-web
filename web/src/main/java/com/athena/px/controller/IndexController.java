package com.athena.px.controller;

import com.athena.px.model.Gg;
import com.athena.px.service.GgService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: ShenLong
 * @CreateDate: 2018/5/15 9:34
 */
@RestController
public class IndexController {

    final GgService ggService;

    public IndexController(GgService ggService) {
        this.ggService = ggService;
    }

    @GetMapping("/jvm-error")
    public String error(){
        while (true){
            System.out.println("1111");
        }
    }

    @GetMapping("/get/{id}")
    public Gg getGg(@PathVariable Integer id){
        return ggService.findGg(id);
    }
}
