package com.forezp.web;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.forezp.entity.user;
import com.forezp.service.AccountService;
import com.forezp.utility.LoginStatus;
import com.sun.deploy.net.HttpResponse;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthDingTalkRequest;
import me.zhyd.oauth.request.AuthGithubRequest;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import me.zhyd.oauth.config.AuthConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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

    @RequestMapping("/render/{source}")
    public void renderAuth(HttpServletResponse response,@PathVariable String source) throws IOException {
        if (source.equals("github")){
            AuthRequest authRequest = getAuthRequest(LoginStatus.getGithub_clientId(),LoginStatus.getGithub_clientSecret(),LoginStatus.getGithub_redirectUri());
            response.sendRedirect(authRequest.authorize(AuthStateUtils.createState()));
        }
        else if (source.equals("dingding")){
            AuthRequest authRequest = getAuthRequestdingtalk(LoginStatus.getDingding_clientId(),LoginStatus.getDingding_clientSecret(),LoginStatus.getDingding_redirectUri());
            response.sendRedirect(authRequest.authorize(AuthStateUtils.createState()));
        }
    }

    @RequestMapping("/callback/{source}")
    public void login(AuthCallback callback,HttpServletRequest request,HttpServletResponse response,@PathVariable String source) throws IOException {
        if (source.equals("github")){
            AuthRequest authRequest = getAuthRequest(LoginStatus.getGithub_clientId(),LoginStatus.getGithub_clientSecret(),LoginStatus.getGithub_redirectUri());
            AuthResponse<AuthUser> authResponse = authRequest.login(callback);
            AuthUser user = authResponse.getData();
            if (accountService.matchinformation(user.getEmail())==null){
                accountService.add(user.getEmail(),"123456",user.getUsername());
            }
            HttpSession session = request.getSession();
            session.setAttribute("username",user.getUsername());
            response.sendRedirect("/blog/article_page");
        }
        else if (source.equals("dingtalk")){
            AuthRequest authRequest = getAuthRequestdingtalk(LoginStatus.getDingding_clientId(),LoginStatus.getDingding_clientSecret(),LoginStatus.getDingding_redirectUri());
            AuthResponse<AuthUser> authResponse = authRequest.login(callback);
            AuthUser user = authResponse.getData();
            if (accountService.matchinformation(user.getUuid()+"@163.com")==null){
                accountService.add(user.getUuid()+"@163.com","123456",user.getUsername());
            }
            HttpSession session = request.getSession();
            session.setAttribute("username",user.getUsername());
            response.sendRedirect("/blog/article_page");
        }

    }

    private AuthRequest getAuthRequest(String clientId,String clientSecret,String redirectUri) {
        return new AuthGithubRequest(AuthConfig.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .redirectUri(redirectUri)
                .build());
    }
    private AuthRequest getAuthRequestdingtalk(String clientId,String clientSecret,String redirectUri) {
        return new AuthDingTalkRequest(AuthConfig.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .redirectUri(redirectUri)
                .build());
    }




}