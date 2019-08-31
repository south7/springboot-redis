package com.example.springbootredis.service;

import com.example.springbootredis.domain.User;

public interface UserService {

    User findUser(int id);

    User findUserByCache(int id);

    User findUserByCacheTtl(int id);
}
