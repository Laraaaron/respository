package com.forezp.utility;

public class LoginStatus {
    /**
     * status 1:注册成功
     * 2:该账号已经存在
     * 3:注册异常
     */
    private static final int status_success = 1;
    private static final int status_fault = 2;
    private static final int status_error = 3;

    public static int getStatus_success() {
        return status_success;
    }

    public static int getStatus_fault() {
        return status_fault;
    }

    public static int getStatus_error() {
        return status_error;
    }

}
