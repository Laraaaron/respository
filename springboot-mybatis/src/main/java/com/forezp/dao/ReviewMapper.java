package com.forezp.dao;

import com.forezp.entity.review;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReviewMapper {
    @Select("select a*,b.user_name from review a join user b on a.user_id =b.user_id and a.article_id=#{article_id} ")
    List<review> findreview(@Param("article_id") Integer article_id);

    @Insert("insert into review(article_reviews, user_id,article_id,article_time) values(#{article_reviews}, #{user_id},#{article_id},#{article_time})")
    int addreview(@Param("article_reviews") String article_reviews,@Param("user_id") Integer user_id,@Param("article_id") Integer article_id,@Param("article_time") String article_time);
}
