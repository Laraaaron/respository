package com.forezp.web;

import com.alibaba.fastjson.JSONObject;
import com.forezp.entity.article;
import com.forezp.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/blog")
public class PensonalController {

    @Autowired
    ArticleService articleService;

    @RequestMapping("/personal")
    public String personal_html(){
        return "link";
    }

    //删除文章
    @RequestMapping("/deleteArticle")
    @ResponseBody
    public String  deleteArticle(HttpServletRequest request){
        Logger logger = Logger.getGlobal();
        String article_id_str = request.getParameter("article_id");
        logger.info("获得的article_id是：" + article_id_str);
        Integer article_id = Integer.valueOf(article_id_str);
        articleService.deleteArticle(article_id);
        return "success";
    }

    //修改文章
    @RequestMapping("/updateArticle")
    @ResponseBody
    public String updateArticle(HttpServletRequest request){
        String article_type = request.getParameter("type");
        String article_text = request.getParameter("text");
        String article_title = request.getParameter("title");
        HttpSession session = request.getSession();
        Integer user_id = Integer.valueOf(session.getAttribute("user_id").toString());
        Date date = new Date();
        String dateFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss").format(date).toString();
        JSONObject result = new JSONObject();
        articleService.updateArticle(article_title, article_text, user_id, dateFormat, Integer.valueOf(article_type));
        return "success";
    }

    @RequestMapping("FindPersonalArticle")
    @ResponseBody
    public String findpersonalarticle(HttpSession session){
        /**
         * 查询个人主页信息
         */
        String user_id=session.getAttribute("user_id").toString();
        JSONObject result = new JSONObject();
        if (user_id !=null){
            List<article> articles =articleService.find_personal_article(Integer.valueOf(user_id));
            result.put("articles",articles);
            result.put("status",true);
            return result.toJSONString();
        }
        result.put("articles"," ");
        result.put("status",false);
        return  result.toJSONString();
    }
}
