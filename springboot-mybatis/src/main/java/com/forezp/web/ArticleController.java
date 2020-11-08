package com.forezp.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.forezp.entity.article;
import com.forezp.service.ArticleService;
import com.mysql.cj.xdevapi.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping(value = "blog")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @RequestMapping(value = "/togetarticle")
    @ResponseBody
    public String findarticle(HttpServletRequest request) {
        Logger logger = Logger.getGlobal();
        String type_str = request.getParameter("type");
        Integer start = Integer.valueOf(request.getParameter("start"));
        logger.info("获取的文章类型为：" + type_str);

        JSONObject result = new JSONObject();
        List<article> article = articleService.findarticle(start,start+10);
        HttpSession session = request.getSession();
        ((JSONObject) result).put("article", article);
        ((JSONObject) result).put("lenth", article.size());
        List<article> hot_article = articleService.hotarticle();
        ((JSONObject) result).put("hotarticle", hot_article);
        ((JSONObject) result).put("hot_lenth", hot_article.size());
        ((JSONObject) result).put("username", session.getAttribute("username"));
        result.put("return_type","All");


        if (type_str != null && !type_str.isEmpty()) {
            Integer article_type = Integer.valueOf(type_str);
            List<article> articles = articleService.findbyarticle(article_type);
            ((JSONObject) result).put("article", articles);
            ((JSONObject) result).put("lenth", articles.size());
            result.put("return_type","seach");
        }


//        for (article value:hot_article){
//            System.out.println(value);
//        }
        return result.toJSONString();
    }

    @RequestMapping(value = "/article_page")
    public String ToArticlePage(HttpServletRequest request, Model model) {
        /**
         * 文章展示页
         */
        HttpSession session = request.getSession();
        Object username = session.getAttribute("username");
        if (username != null) {
            model.addAttribute("name", "Hello," + username.toString());
        } else {
            model.addAttribute("name", "");
        }

        return "article";
    }

    @RequestMapping(value = "/seach")
    @ResponseBody
    public String seacharticle_title(HttpServletRequest request) {
        Logger logger = Logger.getGlobal();
        String article_title = request.getParameter("article_title");
        logger.info("关键词是: " + article_title);
        JSONObject result = new JSONObject();
        List<article> articles = articleService.findbyarticle_title(article_title);
        result.put("article", articles);
        result.put("lenth", articles.size());
        return result.toJSONString();
    }

    @RequestMapping("/write")
    public String write_home(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        model.addAttribute("username", "Hello," + session.getAttribute("username"));
        return "diary";
    }

    @RequestMapping("/addarticle")
    @ResponseBody
    public String addArticle(HttpServletRequest request) {
        String article_type = request.getParameter("type");
        String article_text = request.getParameter("text");
        String article_title = request.getParameter("title");
        HttpSession session = request.getSession();
        Integer user_id = Integer.valueOf(session.getAttribute("user_id").toString());
        Date date = new Date();
        String dateFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss").format(date).toString();
        int result = articleService.addArticle(article_title, article_text, user_id, dateFormat, Integer.valueOf(article_type));
        JSONObject msg = new JSONObject();
        if (result == 1) {
            msg.put("status", true);
        } else {
            msg.put("status", false);
        }
        return msg.toJSONString();
    }


    //通过标签筛选文章
//    @RequestMapping("/Article")
//    @ResponseBody
//    public String getTagArticle(HttpServletRequest request){
//        Logger logger = Logger.getGlobal();
//        String type_str = request.getParameter("type");
//        logger.info("获取的文章类型为：" + type_str);
//        Integer article_type = Integer.valueOf(type_str);
//        JSONObject result = new JSONObject();
//        List<article> articles = articleService.findbyarticle(article_type);
//        result.put("article",articles);
//        result.put("lenth",articles.size());
//        return result.toJSONString();
//    }
}
