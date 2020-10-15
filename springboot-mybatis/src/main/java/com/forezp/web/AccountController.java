package com.forezp.web;

import com.forezp.entity.Account;
import com.forezp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<Account> getAccounts() {
        return accountService.findAccountList();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Account getAccountById(@PathVariable("id") int id) {
        return accountService.findAccount(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updateAccount(@PathVariable("id") int id, @RequestParam(value = "name", required = true) String name,
                                @RequestParam(value = "money", required = true) double money) {
        int t= accountService.update(name,money,id);
        if(t==1) {
            return "success";
        }else {
            return "fail";
        }

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(value = "id")int id) {
        int t= accountService.delete(id);
        if(t==1) {
            return "success";
        }else {
            return "fail";
        }

    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String postAccount(@RequestParam(value = "name") String name,
                              @RequestParam(value = "money") double money) {

       int t= accountService.add(name,money);
       if(t==1) {
           return "success";
       }else {
           return "fail";
       }

    }



}