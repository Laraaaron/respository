package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Day_04_list_learning {
    /**
     * 泛型
     */
    public static void test1(List<String> list) {
        for (String s:list){
            //迭代器便利集合的方式
            System.out.println(s);
        }
    }
    static void test2(List<String> list){
        /**
         * List与Array转换,这种方法会丢失类型信息，所以实际应用比较少
         */
        Object[] array = list.toArray();
        for (Object s:array){
            System.out.println("结果是："+s);
        }
    }
    static void test3(List<String> list){
        /**
         * List与Array转换,第二种方法
         */
        String[] array = list.toArray(new String[list.size()]);
        for (String result:array){
            System.out.println("通过第二种方法转换的结果是："+ result);
        }
    }
    static List<String> test4(){
        /**
         * Array转换成list
         */
        String[] array = new String[]{"apple","banner"};
//        List<String> list = List.of(array); 该方法jdk9以上才支持，可以通过遍历添加
        List<String> list = new ArrayList<>();
        for (String result:array){
            list.add(result);
        }
        return list;

    }
    static void practise(){
        /**
         * 练习题
         */
        final int start =10;
        final int end = 20;
        List<Integer> list = new ArrayList<>();
        for (int i =start;i<end;i++){
            list.add(i);
        }
        //随机删除List中的一个元素
        int removed = list.remove((int)(Math.random()*list.size()));
        System.out.println(removed);
        int found = findMissingNumber(start,end,list,true);
        System.out.println(list.toString());
        System.out.println("missing number:"+ found);
        System.out.println(removed == found ? "测试成功":"测试失败");
    }
    static int findMissingNumber(int start,int end,List<Integer> list){
        /**
         * 给定一组连续的整数，试找出缺失的数字(解法一,Api调用indexof或者contains)
         */
        Integer result = null;
        for (int i =start;i<end;i++){
            if (list.contains(i)==false){
                result = i;
            }
        }
        return result;
    }
    static int findMissingNumber(int start,int end,List<Integer> list,Boolean button){
        /**
         * 解法二,因为其list是有序列表，所以可以通过遍历累加的方式确认是哪个值的缺失
         */
        int t = 0;
        for (int i =start;i<end;i++){
            if (i != list.get(t)){
                return i;
            }
            t+=1;
        }
        return -1;
    }
    public static void main(String args[]){
        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("banner");
        list.add("apple");
//        test3(list);
        practise();
    }
}
