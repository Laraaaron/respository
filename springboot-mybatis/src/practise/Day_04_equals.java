package com.company;

import java.util.ArrayList;
import java.util.List;

public class Day_04_equals {
    /**
     * equals basic functino
     */
    String name;
    public Day_04_equals(String name){
        this.name = name;
    }
    static boolean test1(List<Day_04_equals> list){
        /**
         * 比较list中的两个对象是否相同，对象比较的是引用地址，所以这里会返回false
         */
        return list.contains(new Day_04_equals("Bob"));
    }

    public static void main(String args[]){
        List<Day_04_equals> result = new ArrayList<>();
        result.add(new Day_04_equals("xiaowang"));
        result.add(new Day_04_equals("Bob"));
        System.out.println(test1(result));
        String a = "a";
        System.out.println(a.equals("a"));
    }
}
