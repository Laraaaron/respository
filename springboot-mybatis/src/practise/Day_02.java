package com.company;

public class Day_02 {

    public static String test1(){
        StringBuffer result = new StringBuffer();
        result.append("Hello").append("world");
        return result.toString();
    }

    public static boolean test2(){
        String a = "abc";
        String b=a;
        a = a.toUpperCase();
        return a==b;
    }


    public static void test3(){
        try {
            process1();
        }
        catch (LoginFailedException e){
            e.printStackTrace();
        }
    }


    static void process1(){
        process2();
        throw new LoginFailedException("test");
    }



    static void process2(){
        Integer.parseInt(null);
    }
    public static  void main(String args[]){
//        System.out.println(test1());
//        test2();
        test3();//异常捕获
    }
}
