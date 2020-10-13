package com.company;

public class LoginFailedException extends RuntimeException {
    /**
     * 自定义异常类继承RuntimeException
     */
    LoginFailedException(){
        super();
    }

    LoginFailedException(String msg){
        super(msg);
    }

}

