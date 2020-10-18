package com.forezp.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.forezp.entity.article;
import com.forezp.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "blog")
public class ArticleController {
    @Autowired
    ArticleService articleService;
    @RequestMapping(value = "/togetarticle")
    @ResponseBody
    public String findarticle(){
        JSON result = new JSONObject();
        List<article> article = articleService.findarticle();
        ((JSONObject) result).put("article",article);
        ((JSONObject) result).put("lenth",article.size());
        List<article> hot_article = articleService.hotarticle();
        ((JSONObject) result).put("hotarticle",hot_article);
        ((JSONObject) result).put("hot_lenth",hot_article.size());
//        for (article value:hot_article){
//            System.out.println(value);
//        }
        return result.toJSONString();
    }

    @RequestMapping(value = "/article_page")
    public String ToArticlePage(){
        /**
         * 文章展示页
         */
        return "article";
    }
}
