package com.forezp.dao;

import com.forezp.entity.article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleMapper {
    @Select("select * from article")
    List<article> findarticle();

    @Select("select * from article order by article_comments_like desc limit 10")
    List<article> hotarticle();

    @Select("select * from article where article_type = #{article_type}")
    List<article> findbyarticle(@Param("article_type") Integer article_type);

    @Select("select * from article where article_title like concat('%',#{article_title},'%')")
    List<article> findbyarticle_title(@Param("article_title") String article_title);
}
