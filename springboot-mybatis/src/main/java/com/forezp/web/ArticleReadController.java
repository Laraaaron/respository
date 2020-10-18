package com.forezp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/blog")
public class ArticleReadController {
    @RequestMapping(value = "/article_read")
    public String ToArticleRead(){
        /**
         * 文章详情页
         */
        return "read";
    }

}
