package com.zhr.test;

/**
 * 懒汉式的变种
 * 内部静态类 既实现了线程安全 也避免了线程同步带来的性能问题
 */
public class Singleton4 {

    private Singleton4(){}

    private static class lazyHolder{

        private static final Singleton4 SINGLE = new Singleton4();
    }

    public static Singleton4 getInstace(){

        return lazyHolder.SINGLE;
    }
}
