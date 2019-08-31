package com.example.springbootredis.service;

import org.springframework.data.redis.core.ZSetOperations;

import java.util.List;
import java.util.Set;

public interface RedisService {

     boolean setBit(String key, long offset, boolean isShow);

     boolean getBit(String key, long offset);

     boolean set(final String key, Object value);

     boolean set(final String key, Object value, Long expireTime);

     void remove(final String... keys);

     void remove(final String key);

     boolean exists(final String key);

     Object get(final String key);

     void hmSet(String key, Object hashKey, Object value);

     Object hmGet(String key, Object hashKey);

     void lPush(String k, Object v);

     List<Object> lRange(String k, long l, long l1);

     void add(String key, Object value);

     Set<Object> setMembers(String key);

     void zAdd(String key, Object value, double score);
    
     Set<Object> rangeByScore(String key, double score, double score1);

    void incrementScore(String key, Object value, Integer score);

     Long zRank(String key, Object value);

     Double zScore(String key, Object value);

     Set<ZSetOperations.TypedTuple<Object>> zRankWithScore(String key, Integer start, Integer end);
}
