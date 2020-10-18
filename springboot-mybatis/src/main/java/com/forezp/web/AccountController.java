package com.forezp.web;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.forezp.entity.user;
import com.forezp.service.AccountService;
import com.forezp.utility.LoginStatus;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Logger;

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
        Logger logger = Logger.getGlobal();
        String user_email=request.getParameter("user_email");
        String user_password=request.getParameter("user_password");
        user result =accountService.findAccount(user_email,user_password);
        logger.info(result.toString());
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
        user result = accountService.matchinformation(user_email);
        JSONObject msg =new JSONObject();
        LoginStatus status = new LoginStatus();
        if (result == null){
           int t= accountService.add(user_email,user_password,user_name);
           if(t==1) {
               msg.put("status",LoginStatus.getStatus_success());
               return msg.toJSONString();
           }else {
               msg.put("status",LoginStatus.getStatus_error());
               return msg.toJSONString();
           }
       }
       else {
            msg.put("status",LoginStatus.getStatus_fault());
            return msg.toJSONString();
        }

    }



}