package com.athena.px.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.iu.sl.api.BlogService;
import com.iu.sl.model.Blog;
import com.iu.sl.pojo.SLResponse;
import com.iu.sl.pojo.blog.RedisSLResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description:
 * @Author: ShenLong
 * @CreateDate: 2018/5/15 9:34
 */
@Controller
public class IndexController {

    @Value("${athena.host}")
    public String hostAddress;

    @Reference
    private BlogService blogService;

    private final KafkaTemplate kafkaTemplate;

    @Autowired
    public IndexController(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("host",hostAddress);
        return "/index";
    }


    @GetMapping(value = "/blog/list")
    @ResponseBody
    public List<Blog> finAll(){
        SLResponse response = blogService.findAll();
        RedisSLResponse<List<Blog>> redisSLResponse = null;
        if(response instanceof RedisSLResponse){
            redisSLResponse = (RedisSLResponse) response;
        }
        return redisSLResponse.getResult();
    }

    @GetMapping(value = "/send/{message}")
    @ResponseBody
    public String sendMessage(@PathVariable String message){
        JSONObject jo = new JSONObject();
        jo.put("message",message);
        kafkaTemplate.send("mailTopic",jo.toJSONString());
        return message;
    }
}
