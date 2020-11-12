package com.forezp.dao;

import com.forezp.entity.article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {
    @Select("select a.*,b.talk_nums from article a left join " +
            "(select article_id,count(*) talk_nums from review group by article_id ) b" +
            " on a.article_id = b.article_id limit #{start},#{end}")
    List<article> findarticle(@Param("start") Integer start,@Param("end") Integer end);

    @Select("select * from article order by article_comments_like desc limit 10")
    List<article> hotarticle();

    @Select("select a.*,b.talk_nums from article a left join " +
            "(select article_id,count(*) talk_nums from review group by article_id ) b on " +
            "a.article_id = b.article_id where article_type = #{article_type}")
    List<article> findbyarticle(@Param("article_type") Integer article_type);

    @Select(" select a.*,b.talk_nums from article a left join " +
            "(select article_id,count(*) talk_nums from review group by article_id ) b on " +
            "a.article_id = b.article_id where article_title like concat('%',#{article_title},'%')")
    List<article> findbyarticle_title(@Param("article_title") String article_title);

    @Select("select a.*,b.user_name from article a join user b on a.user_id = b.user_id where article_id=#{article_id}")
    List<article> findbyarticle_id(@Param("article_id") Integer article_id);

    @Insert("insert into article(article_title, article_text,user_id,article_time,article_type) values(#{article_title}, #{article_text},#{user_id},#{article_time},#{article_type})")
    int addArticle(@Param("article_title") String article_title,@Param("article_text") String article_text,@Param("user_id") Integer user_id,@Param("article_time") String article_time,@Param("article_type") Integer article_type );


    @Delete("delete from article where article_id = #{article_id}")
    void deleteArticle(@Param("article_id")Integer article_id );

    @Update("update from article set article_title=#{article_title},article_text=#{article_text},article_type=#{article_type} where article_id=#{article_id}")
    List<article> updateArticle(@Param("article_title") String article_title,@Param("article_text") String article_text,@Param("user_id") Integer user_id,@Param("article_time") String article_time,@Param("article_type") Integer article_type);

    @Update("update article set article_comments_like = #{article_comments_like} where article_id = #{article_id}")
    int update_article_comments_like(@Param("article_comments_like") Integer article_comments_like, @Param("article_id") Integer article_id);
}
