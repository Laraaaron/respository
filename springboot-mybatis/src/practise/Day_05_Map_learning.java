package com.company;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day_05_Map_learning {
    static void test1() {
        Map<String, String> test = new HashMap<>();
        test.put("name","xiaohong");
        test.put("age","16");
        for (String a:test.keySet()){
            System.out.println(a);
        }
        for (Map.Entry<String,String> entry:test.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key+":"+value);
        }
    }

    public static void main(String args[]){
        test1();
    }
}
