package com.forezp.web;

import com.forezp.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "blog")
public class ArticleController {
    @Autowired
    ArticleService articleService;
    @RequestMapping(value = "/togetarticle")
    @ResponseBody
    public String findarticle(){
        return articleService.findarticle().toString();
    }

    @RequestMapping(value = "/article_page")
    public String ToArticlePage(){
        /**
         * 文章展示页
         */
        return "article";
    }
}
