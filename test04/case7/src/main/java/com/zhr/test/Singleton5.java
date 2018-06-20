package com.zhr.test;

import java.io.Serializable;

/**
 * 饿汉式
 * 直接创建对象 以空间换时间
 */
public class Singleton5 implements Serializable{

    private Singleton5(){}

    private static final Singleton5 SINGLE = new Singleton5();

    public static Singleton5 getInstace(){

        return SINGLE;
    }
}
