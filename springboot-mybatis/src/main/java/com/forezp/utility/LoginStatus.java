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

    private static final String github_clientId ="4af3163d80977f3ebe78";
    private static final String github_clientSecret="3ee1cb5790c17b9c2b7b38134357fd1718221809";
    private static final String github_redirectUri="http://127.0.0.1:8080/blog/callback/github";

    private static final String dingding_clientId ="dingoan8wsqmhze7kqgw7w";
    private static final String dingding_clientSecret="dU86Ikf5UzN1PloTKhemseSqBV4uI3tKsessVj9ytPzitCr9EcD5wwEjnXb3KEPS";
    private static final String dingding_redirectUri="http://127.0.0.1:8080/blog/callback/dingtalk";

    public static int getStatus_success() {
        return status_success;
    }

    public static int getStatus_fault() {
        return status_fault;
    }

    public static int getStatus_error() {
        return status_error;
    }

    public static String getGithub_clientId() {
        return github_clientId;
    }

    public static String getGithub_clientSecret() {
        return github_clientSecret;
    }

    public static String getGithub_redirectUri() {
        return github_redirectUri;
    }

    public static String getDingding_clientId() {
        return dingding_clientId;
    }

    public static String getDingding_clientSecret() {
        return dingding_clientSecret;
    }

    public static String getDingding_redirectUri() {
        return dingding_redirectUri;
    }
}
