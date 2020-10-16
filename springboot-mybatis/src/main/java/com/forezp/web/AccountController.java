package com.forezp.web;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.forezp.entity.Account;
import com.forezp.entity.user;
import com.forezp.service.AccountService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by fangzhipeng on 2017/4/20.
 */
@Controller
@RequestMapping("/blog")
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/home",method = {RequestMethod.GET})
    public String ToHomePage() {
        /**
         * 首页
         */
        return "index";
    }

    @RequestMapping(value = "/article_page")
    public String ToArticlePage(){
        /**
         * 文章展示页
         */
        return "article";
    }

    @RequestMapping(value = "/article_read")
    public String ToArticleRead(){
        /**
         * 文章详情页
         */
        return "read";
    }

    @RequestMapping(value="/signin")
    public String SignIn(){
        /**
         * 登陆页面
         */
        return "login";
    }
//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    @ResponseBody
//    public List<Account> getAccounts() {
//        return accountService.findAccountList();
//    }

    @RequestMapping(value = "/checkmsg", method = RequestMethod.POST)
    @ResponseBody
    public String getAccountById(HttpServletRequest request) {
        /**
         * 验证用户登陆信息
         */
        String user_email=request.getParameter("user_email");
        String user_password=request.getParameter("user_password");
        user result =accountService.findAccount(user_email,user_password);
        System.out.println(user_email+user_password);
        System.out.println(result);
        JSONObject msg =new JSONObject();
        if (result != null){
            msg.put("status",true);
            msg.put("user_name",result.getUser_name());
            HttpSession session = request.getSession();
            session.setAttribute("username",result.getUser_name());
            return msg.toJSONString();
        }
        else {
            msg.put("status",false);
            return msg.toJSONString();
        }
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    public String updateAccount(@PathVariable("id") int id, @RequestParam(value = "name", required = true) String name,
//                                @RequestParam(value = "money", required = true) double money) {
//        int t= accountService.update(name,money,id);
//        if(t==1) {
//            return "success";
//        }else {
//            return "fail";
//        }
//
//    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public String delete(@PathVariable(value = "id")int id) {
//        int t= accountService.delete(id);
//        if(t==1) {
//            return "success";
//        }else {
//            return "fail";
//        }
//
//    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ResponseBody
    public String postAccount(HttpServletRequest request) {
        /**
         * 用户注册入口
         */
        String user_name=request.getParameter("user_name");
        String user_email=request.getParameter("user_email");
        String user_password=request.getParameter("user_password");
//        System.out.println("user_name:"+user_name+"user_email:"+user_email+"user_password:"+user_password);
       int t= accountService.add(user_email,user_password,user_name);
        JSONObject msg =new JSONObject();
       if(t==1) {
           msg.put("status",true);
           return msg.toJSONString();
       }else {
           msg.put("status",false);
           return msg.toJSONString();
       }

    }



}