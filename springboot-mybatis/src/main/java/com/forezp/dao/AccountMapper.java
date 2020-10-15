package com.forezp.dao;

import com.forezp.entity.Account;
import com.forezp.entity.user;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * update by hongchao on 2020/10/15.
 */
@Mapper
public interface AccountMapper {

    @Insert("insert into user(user_email, user_password,user_name) values(#{user_email}, #{user_password},#{user_name})")
    int add(@Param("user_email") String user_email, @Param("user_password") String user_password,@Param("user_name") String user_name);

//    @Update("update account set name = #{name}, money = #{money} where id = #{id}")
//    int update(@Param("name") String name, @Param("money") double money, @Param("id") int  id);
//
//    @Delete("delete from account where id = #{id}")
//    int delete(int id);

    @Select("select user_id, user_name from user where user_email = #{user_email} and user_password= #{user_password}")
    user findAccount(@Param("user_email") String user_email, @Param("user_password") String user_password);

//    @Select("select id, name as name, money as money from account")
//    List<Account> findAccountList();
}