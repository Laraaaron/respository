package com.forezp.service;

import com.forezp.dao.MsgSever;
import com.forezp.entity.article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MsgServiceImpl implements MsgSever {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void setMsg(String key, List<article> msg) {
        redisTemplate.opsForValue().set(key,msg);
    }

    @Override
    public List<article> getMsg(String key) {

        return (List<article>) redisTemplate.opsForValue().get(key);
    }
}
