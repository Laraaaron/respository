package com.forezp.web;

import com.forezp.entity.article;
import com.forezp.service.ArticleService;
import com.sun.org.glassfish.gmbal.ParameterNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping(value = "/blog")
public class ArticleReadController {
    @Autowired
    ArticleService articleService;
    @RequestMapping(value = "/article_read",method = RequestMethod.GET)
    public String ToArticleRead(HttpServletRequest request,Model model){
        /**
         * 文章详情页
         */
        Logger logger = Logger.getGlobal();
        String id = request.getParameter("article_id");
        logger.info("获取的id是:"+id);
        List<article> articles =articleService.findbyarticle_id(Integer.valueOf(id));
        String[] result = articles.get(0).getArticle_time().split("-");
        String year = result[0].toString();
        String month = result[1].toString();
        String day = result[2].split(" ")[0].toString();
        model.addAttribute("year",year);
        model.addAttribute("month",month);
        model.addAttribute("day",day);
        model.addAttribute("article",articles);
        HttpSession session = request.getSession();
        Object name = session.getAttribute("username" );
        if (name!=null){
            model.addAttribute("name","Hello,"+name.toString());
        }
        else {
            model.addAttribute("name","");
        }

        return "read";
    }

}
