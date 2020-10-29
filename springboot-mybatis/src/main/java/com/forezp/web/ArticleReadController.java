package com.forezp.web;

import com.alibaba.fastjson.JSONObject;
import com.forezp.dao.ReviewMapper;
import com.forezp.entity.article;
import com.forezp.entity.review;
import com.forezp.service.ArticleService;
import com.forezp.service.ReviewService;
import com.sun.org.glassfish.gmbal.ParameterNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping(value = "/blog")
public class ArticleReadController {
    @Autowired
    ArticleService articleService;
    @Autowired
    ReviewMapper reviewMapper;
    @Autowired
    ReviewService reviewService;

    @RequestMapping(value = "/article_read", method = RequestMethod.GET)
    public String ToArticleRead(HttpServletRequest request, Model model) {
        /**
         * 文章详情页
         */
        Logger logger = Logger.getGlobal();
        String id = request.getParameter("article_id");
        logger.info("获取的id是:" + id);
        List<article> articles = articleService.findbyarticle_id(Integer.valueOf(id));
        String[] result = articles.get(0).getArticle_time().split("-");
        String year = result[0].toString();
        String month = result[1].toString();
        String day = result[2].split(" ")[0].toString();
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("day", day);
        model.addAttribute("article", articles);
        HttpSession session = request.getSession();
        Object name = session.getAttribute("username");
        if (name != null) {
            model.addAttribute("name", "Hello," + name.toString());
        } else {
            model.addAttribute("name", "");
        }
        List<review> reviews = reviewMapper.findreview(Integer.valueOf(id));
        model.addAttribute("reviews", reviews);
        return "read";
    }


    @RequestMapping(value = "/comment", method = {RequestMethod.POST})
    @ResponseBody
    public String Add_comment(HttpServletRequest request) throws ParseException {
        /**
         * 添加文章详情页评论
         */
        Logger logger = Logger.getGlobal();
        HttpSession session = request.getSession();
        int user_id = Integer.valueOf(session.getAttribute("user_id").toString());
        String review = request.getParameter("article_review");
        int article_id = Integer.valueOf(request.getParameter("article_id"));
        logger.info("获取用户id是：" + user_id);

        //获取当前系统时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time_str = format.format(new Date());
        //Date current_time = format.parse(time_str);

        if (null != review) {
            reviewService.addreview(review, user_id, article_id, time_str);
        }

        //添加评论后查询所有的评论
        List<review> reviews = reviewMapper.findreview(Integer.valueOf(article_id));
        JSONObject result = new JSONObject();
        result.put("review",reviews);
        result.put("lenth",reviews.size());
        return result.toJSONString();

    }
}
