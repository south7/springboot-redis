package com.example.springbootredis.controller;

import com.example.springbootredis.domain.User;
import com.example.springbootredis.mapper.UserMapper;
import com.example.springbootredis.service.RedisService;
import com.example.springbootredis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private static final String key = "userCache_";

    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;

    @RequestMapping("/findUser")
    public User getUser(int id) {
        User user = userService.findUser(id);
        return user;
    }
    /**
     * set值和get值的时候序列化方式必须保持一致
     * 这里直接操作redis
     * @param id
     * @return
     */
    @RequestMapping("/findUserByRedis")
    public User findUserByRedis(int id) {
        //step1 先从redis里面取值
        User user =  (User)redisService.get(key + id);
        //step2 如果拿不到则从DB取值
        if (user == null) {
            User userDB = userService.findUser(id);
            System.out.println("fresh value from DB id:" + id);
            //step3 DB非空情况刷新redis值
            if (userDB != null) {
                redisService.set(key + id, userDB);
                return userDB;
            }
        }
        return user;
    }

    /**
     * springboot cache去操作redis
     * @param id
     * @return
     */
    @RequestMapping("/findUserByCache")
    public User findUserByCache(int id) {
        User user = userService.findUserByCache(id);
        return user;
    }

    @RequestMapping(value = "/findUserByCacheTtl")
    public User findUserByCacheTtl(int id) {
        User u = new User();
        u = userService.findUserByCacheTtl(id);
        return u;
    }
}
