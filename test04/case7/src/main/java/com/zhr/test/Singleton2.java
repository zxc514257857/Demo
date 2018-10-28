package com.zhr.test;

/**
 * 懒汉式的变种
 * 在getInstance前面加上synchronized关键字，但每次调用getInstance方法的时候都会去同步，消耗系统资源
 */
public class Singleton2 {

    private Singleton2(){}

    private static Singleton2 single = null;

    public static synchronized Singleton2 getInstance(){

        if(single == null){
            single = new Singleton2();
        }
        return single;
    }
}
