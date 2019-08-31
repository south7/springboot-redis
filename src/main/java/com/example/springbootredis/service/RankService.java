package com.example.springbootredis.service;

import org.springframework.data.redis.core.ZSetOperations;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RankService {

    void addRank(int uid, Integer score);

    Long score(int uid);

    Long rankNum(int uid);

    void increSocre(int uid, Integer score);

    Set<ZSetOperations.TypedTuple<Object>> rankWithScore(Integer start, Integer end);
}
