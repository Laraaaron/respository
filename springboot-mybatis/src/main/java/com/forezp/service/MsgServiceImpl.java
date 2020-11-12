package com.forezp.service;

import com.forezp.dao.MsgSever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class MsgServiceImpl implements MsgSever {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean setMsg(String key, Integer msg) {
        redisTemplate.opsForValue().set(key,msg);
        return true;
    }

    @Override
    public Integer getMsg(String key) {
        return Integer.valueOf(redisTemplate.opsForValue().get(key).toString());
    }
}
