package com.forezp.web;

import com.forezp.dao.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Set;

@Component
public class ScheduleRunner {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    ArticleMapper articleMapper;
@Scheduled(fixedDelay = 50000)
    public void job1(){
    System.out.println(Thread.currentThread() +", job start@" +LocalTime.now());
    Set<String> keys = redisTemplate.keys("*");
    for (String key:keys){
        int value = Integer.valueOf(redisTemplate.opsForValue().get(key).toString());
        int article_id = Integer.valueOf(key.split("_")[2]);
        int nums = articleMapper.findbyarticle_id(article_id).get(0).getArticle_comments_like();
        articleMapper.update_article_comments_like(value + nums, article_id);
        redisTemplate.opsForValue().set("article_id_" + article_id, 0);

    }
    System.out.println(Thread.currentThread() +", job finish@" +LocalTime.now());
}
}
