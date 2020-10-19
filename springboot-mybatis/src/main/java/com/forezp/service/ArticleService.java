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

    @Override
    public List<article> findbyarticle_title(String article_title) {
        return articleMapper.findbyarticle_title(article_title);
    }

    @Override
    public List<article> findbyarticle_id(Integer article_id) {
        return articleMapper.findbyarticle_id(article_id);
    }
}
