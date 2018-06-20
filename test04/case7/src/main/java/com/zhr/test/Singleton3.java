package com.zhr.test;

/**
 * 懒汉式的变种
 * 双重判断加锁 同步的判断只是在第一次调用的时候 避免了每次调用时的同步判断 节约了系统资源
 */
public class Singleton3 {

    private Singleton3(){}

    private static volatile Singleton3 single = null;

    public static Singleton3 getInstace(){

        if(single == null){

            synchronized (Singleton3.class){
                if(single == null){
                    single = new Singleton3();
                }
            }
        }

        return single;
    }
}
