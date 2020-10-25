package com.forezp.dao;

import com.forezp.entity.article;
import org.apache.ibatis.annotations.Insert;
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

    @Select("select a.*,b.user_name from article a join user b on a.user_id = b.user_id where article_id=#{article_id}")
    List<article> findbyarticle_id(@Param("article_id") Integer article_id);

    @Insert("insert into article(article_title, article_text,user_id,article_time,article_type) values(#{article_reviews}, #{user_id},#{article_id},#{article_time})")
    int addArticle(@Param("article_title") String article_title,@Param("article_text") String article_text,@Param("user_id") Integer user_id,@Param("article_time") String article_time,@Param("article_type") Integer article_type );

}
