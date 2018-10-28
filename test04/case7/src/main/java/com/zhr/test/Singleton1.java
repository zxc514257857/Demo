package com.zhr.test;

/**
 * 懒汉式：先判断再实例化
 * 但是在多线程的情况下会出现问题
 */
public class Singleton1{

    private Singleton1(){}

    private static Singleton1 single = null;

    public static Singleton1 getInstance(){

        if(single == null){
            single = new Singleton1();
        }
        return single;
    }
}