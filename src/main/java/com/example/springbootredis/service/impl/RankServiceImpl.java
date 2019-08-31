package com.example.springbootredis.service.impl;

import com.example.springbootredis.mapper.UserMapper;
import com.example.springbootredis.service.RankService;
import com.example.springbootredis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RankServiceImpl implements RankService {
    private static final String RANKGNAME = "user_score";

    private static final String SALESCORE = "sale_score_rank:";

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserMapper userMapper;


    @Override
    public void addRank(int uid, Integer score) {
        redisService.zAdd(RANKGNAME, uid, score);
    }

    @Override
    public Long score(int uid) {
        Long score = redisService.zScore(RANKGNAME, uid).longValue();
        return score;
    }

    @Override
    public Long rankNum(int uid) {
        return redisService.zRank(RANKGNAME, uid);
    }

    @Override
    public void increSocre(int uid, Integer score) {
        redisService.incrementScore(RANKGNAME, uid, score);
    }

    @Override
    public Set<ZSetOperations.TypedTuple<Object>> rankWithScore(Integer start, Integer end) {
        return redisService.zRankWithScore(RANKGNAME, start, end);
    }


}
