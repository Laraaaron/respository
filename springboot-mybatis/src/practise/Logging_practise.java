package com.company;
import java.util.logging.Logger;
import java.util.logging.Level;
public class Logging_practise {
    /**
     * day03，主要讲解一下简单的log打印日志的方法
     * 优先级Server,Warning,Info,Config.....(Info以下的日志不会被打印出来，需要输出日志定义级别)
     */
    public static void main(String args[]){
        Logger logger = Logger.getGlobal();
        logger.info("start process......");
        logger.warning("memory is" +
                "running out");
        logger.severe("process will be terminated");
    }
}
