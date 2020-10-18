package com.forezp.dao;

import com.forezp.entity.article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface ArticleMapper {
    @Select("select * from article")
    article findarticle();
}
