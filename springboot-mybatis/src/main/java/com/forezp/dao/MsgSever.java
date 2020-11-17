package com.forezp.dao;

import com.forezp.entity.article;

import java.util.List;

public interface MsgSever {
    public void setMsg(String key,List<article> msg);
    public List<article> getMsg(String key);
}
