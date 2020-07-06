package com.company;

public class MessageStructure {
    //基本数据类型 整型byte，short，int，long
    //浮点型  float，double
    //布尔型 Boolean
    //字符类型 char
    int i =  1;
    long  ii   =  1l;
    float  t  =  2f;
    boolean a  = true;
    char   b  = 'A';
    //引用类型
    String c  = "hello world";
    //常量:常量初始化后不可再次进行赋值操作
    final   int  PI = 1;

    public void IntSizeFunction(int a,int b){
        /*整型运算，注意强转，即类型小的会自动转为大的，大的强转转移小的注意会抛掉字节*/
    int  result   =  a+b;
    int  result2 =  a/b;
    int result3  =  a%b;
    System.out.println(result);
    System.out.println(result2);
    System.out.println(result3);
}
public void DoubleSize(double  a,double b){
    /*浮点型数据无法做精准判断，所以一般用绝对值误差来表示，需要注意的是对于复杂运算，两个整型的运算不会出现自动提升的情况*/
    if (Math.abs(a-b)<0.00001){
        System.out.print("a=b");
    }
    else{
        a = 1.5+8/3;
        System.out.print(a);
    }
    }
    private void BooleanSize(){
        /*布尔运算,需要注意的是其的短路功能*/
        boolean a = 5<3;
        boolean b = a  &&  (5/0>0);
        boolean c  =  !a  ||  (5/0>0);
        System.out.println(b);
        System.out.println(c);
        /*另外再谈谈其三元运算符*/
        int test   =  -100;
        int x  = test>=0  ?  test:-test;
        System.out.println(x);
    }
    public void practise(){
        /*练习,计算一元二次方程的两个解*/
        double a = 1.0;
        double  b  =  3.0;
        double  c = -4.0;
        double r1 =  (-b  +  Math.sqrt(b*b-4*a*c))/2*a;
        double  r2 =  (-b  -  Math.sqrt(b*b-4*a*c))/2*a;
        System.out.println((int)r1);
        System.out.println((int)r2);
        /*练习2，判断年龄是否为小学生*/
        int age = 14;
        // primary student的定义: 6~12岁
        boolean isPrimaryStudent = age>6 &&  age<12;
        System.out.println(isPrimaryStudent ? "Yes" : "No");

    }
public static  void  main(String args[]){
        MessageStructure  test =   new MessageStructure();
        test.IntSizeFunction(8,4);
        test.practise();
        test.BooleanSize();
}
}

