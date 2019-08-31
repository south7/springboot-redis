package com.example.springbootredis.controller;

import com.example.springbootredis.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class RankController {
    @Autowired
    private RankService rankService;

    @ResponseBody
    @RequestMapping("/addRank")
    public String addRank(int uid, Integer score) {
        rankService.addRank(uid, score);
        return "success";
    }

    @RequestMapping("/score")
    public Long score(int uid) {
        return rankService.score(uid);
    }

    @RequestMapping("/rank")
    public Map<Integer, Long> rank(int uid) {
        Map<Integer, Long> map = new HashMap<>();
        map.put(uid, rankService.rankNum(uid));
        return map;
    }

    @RequestMapping("/increScore")
    public String increScore(int uid, Integer score) {
        rankService.increSocre(uid, score);
        return "success";
    }

    @RequestMapping("/scoreByRange")
    public Set<ZSetOperations.TypedTuple<Object>> scoreByRange(Integer start, Integer end) {
        return rankService.rankWithScore(start,end);
    }
}
