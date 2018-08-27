//package com.athena.px.springbootweb.controller;
//
//import com.athena.px.springbootweb.domain.User;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @Description:
// * @Author: ShenLong
// * @CreateDate: 2018/4/21 16:36
// */
//@RestController
//public class RestControllerDemo {
//
//    /*@GetMapping("/{id}")
//    public String hello(@PathVariable String id){
//        return id;
//    }*/
//
//    @PostMapping(value = "/json/properties",
//    consumes = "application/json",
//    produces = "application/properties")
//    public User jsonToProperties(@RequestBody User user){
//        return user;
//    }
//
//    @PostMapping(value = "/properties/json",
//            consumes = "application/properties",
//            produces = "application/json")
//    public User propertiesToJson(@RequestBody User user){
//        return user;
//    }
//}
