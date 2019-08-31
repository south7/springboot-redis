package com.example.springbootredis.mapper;


import com.example.springbootredis.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("select id,username from user where id=#{id} ")
    User findUser(@Param("id") int id);

    @Select("select id,username from user where id=#{id} ")
    User findUserByCache(int id);

    @Select("select id,username from user where id=#{id} ")
    User findUserByCacheTtl(int id);
}
