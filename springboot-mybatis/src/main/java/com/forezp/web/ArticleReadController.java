package com.forezp.web;

import com.sun.org.glassfish.gmbal.ParameterNames;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@Controller
@RequestMapping(value = "/blog")
public class ArticleReadController {
    @RequestMapping(value = "/article_read",method = RequestMethod.GET)
    public String ToArticleRead(HttpServletRequest request){
        /**
         * 文章详情页
         */
        Logger logger = Logger.getGlobal();
        String id = request.getParameter("article_id");
        logger.info("获取的id是:"+id);
        return "read";
    }

}
