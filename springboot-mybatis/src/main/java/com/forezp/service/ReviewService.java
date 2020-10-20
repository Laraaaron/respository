package com.forezp.service;

import com.forezp.dao.ReviewMapper;
import com.forezp.entity.review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService implements ReviewMapper {
    @Autowired
    ReviewMapper reviewMapper;
    @Override
    public List<review> findreview(Integer article_id) {
        return reviewMapper.findreview(article_id);
    }

    @Override
    public int addreview(String article_reviews, Integer user_id, Integer article_id,String article_time) {
        return reviewMapper.addreview(article_reviews,user_id,article_id,article_time);
    }
}
