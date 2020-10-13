package com.company;

public class FuctionMaken {
    /*day02,本章节主要讲述function的构造*/
    private String name;
    private int age;

    public void setName(String name) throws IllegalAccessException {
        if (name == null && name.isEmpty()){
            throw new IllegalAccessException("error name");
        }
        this.name = name;
    }

    public void setAge(int age) throws IllegalAccessException {
        if (age>99 || age<0){
            throw new IllegalAccessException("error age");
        }
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getName(String name){
        /*重载*/
        if (this.name == name){
            return "名称相同"+this.name;
        }
        return "名称不相同"+name;
    }
    public FuctionMaken(String name,int age){
        /*构造方法*/
        this.name=name;
        this.age=age;
    }
    public static void main(String args[]){
        FuctionMaken a = new FuctionMaken("xiaowang",16);

    }
}
