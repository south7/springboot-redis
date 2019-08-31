package com.example.springbootredis.controller;

import com.example.springbootredis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RedisController {

    @Autowired
    private RedisService redisService;

    @RequestMapping("/redis/setAndGet")
    public String setAndGetValue(String name,String value){
        System.out.println("name: "+name+" ,value: "+value);
        redisService.set(name,value);
        return redisService.get(name).toString();
    }


}
