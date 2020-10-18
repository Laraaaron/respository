package com.forezp.service;

import com.forezp.dao.ArticleMapper;
import com.forezp.entity.article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService implements ArticleMapper {
    @Autowired
    ArticleMapper articleMapper;
    @Override
    public List<article> findarticle() {
        return articleMapper.findarticle();
    }

    @Override
    public List<article> hotarticle() {
        return articleMapper.hotarticle();
    }

    @Override
    public List<article> findbyarticle(Integer article_type) {
        return articleMapper.findbyarticle(article_type);
    }
}
