package com.company;

public class FuctionMakenReload extends FuctionMaken implements test {
    public static int count=0;
    //实例数量
    protected int score;
    FuctionMakenReload(String name,int age,int score){
        super(name,age);
        this.score = score;
        FuctionMakenReload.count +=1;
//        实例话对象，计数加一
    }
    @Override
    public String getName(String name){
        return name + "son";
    }


    public static void main(String args[]){
        FuctionMaken a = new FuctionMakenReload("xiaohong",16,88);
        ((FuctionMakenReload) a).score = 90;
        System.out.println(((FuctionMakenReload) a).score);
        FuctionMaken b = new FuctionMakenReload("laowang",18,90);
        System.out.print("当前的实例数量为："+FuctionMakenReload.getCount());
    }

    @Override
    public void address() {
        System.out.println("success");
    }

    public static int getCount() {
        return count;
    }
}
