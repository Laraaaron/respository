package com.forezp.service;

import com.forezp.dao.AccountMapper;
//import com.forezp.entity.Account;
import com.forezp.entity.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fangzhipeng on 2017/4/20.
 */
@Service
public class AccountService  implements AccountMapper{
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public int add(String user_email, String user_password, String user_name) {
        return accountMapper.add(user_email,user_password,user_name);
    }

    @Override
    public user matchinformation(String user_email) {
        return accountMapper.matchinformation(user_email);
    }

    @Override
    public user findAccount(String user_email, String user_password) {
        return accountMapper.findAccount(user_email,user_password);
    }

//    public int add(String name, double money) {
//        return accountMapper.add(name, money);
//    }
//    public int update(String name, double money, int id) {
//        return accountMapper.update(name, money, id);
//    }
//    public int delete(int id) {
//        return accountMapper.delete(id);
//    }
//    public Account findAccount(int id) {
//        return accountMapper.findAccount(id);
//    }
//    public List<Account> findAccountList() {
//        return accountMapper.findAccountList();
//    }

}