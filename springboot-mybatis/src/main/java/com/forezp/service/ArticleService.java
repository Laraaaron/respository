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
    public List<article> findarticle(Integer start,Integer end) {
        return articleMapper.findarticle(start,end);
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

    @Override
    public int addArticle(String article_title, String article_text, Integer user_id, String article_time, Integer article_type) {
        return articleMapper.addArticle(article_title,article_text,user_id,article_time,article_type);
    }

    @Override
    public void deleteArticle(Integer article_id){
        articleMapper.deleteArticle(article_id);
    }

    @Override
    public List<article> updateArticle(String article_title, String article_text, Integer user_id, String article_time, Integer article_type){
        return articleMapper.updateArticle(article_title,article_text,user_id,article_time,article_type);
    }

    @Override
    public int update_article_comments_like(Integer article_comments_like, Integer article_id) {
        return articleMapper.update_article_comments_like(article_comments_like,article_id);
    }
}
