package com.example.springbootredis.service.impl;

import com.example.springbootredis.domain.User;
import com.example.springbootredis.mapper.UserMapper;
import com.example.springbootredis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@CacheConfig(cacheNames="userInfoCache") // 本类内方法指定使用缓存时，默认的名称就是userInfoCache
@Transactional(propagation= Propagation.REQUIRED,readOnly=false,rollbackFor=Exception.class)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUser(int id) {
        return userMapper.findUser(id);
    }


    @Override
    @Nullable
    @Cacheable(key="#p0") // @Cacheable 会先查询缓存，如果缓存中存在，则不执行方法
    public User findUserByCache(int id) {
        System.err.println("根据id=" + id +"获取用户对象，从数据库中获取");
        return userMapper.findUserByCache(id);
    }

    @Override
    @Nullable
    @Cacheable(value = "oneUserInfo", keyGenerator = "simpleKeyGenerator")
    public User findUserByCacheTtl(int id) {
        return userMapper.findUserByCacheTtl(id);
    }
}
