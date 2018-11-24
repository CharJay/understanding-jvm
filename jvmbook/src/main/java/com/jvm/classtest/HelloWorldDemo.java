package com.jvm.classtest;

import java.io.File;

/**
 * 一个类可以抽象为：数据、指令、控制
 */
public class HelloWorldDemo {
    //常量、静态变量
    private final int i=0;
    private static int k=0;
    //成员变量
    private Object obj = new Object();
    private int sss=0;

    //局部变量
    public void methodOne(int i){
        int j=0;
        int sum=i+j;
        Object acb =obj;
        long start = System.currentTimeMillis();
        methodTwo();
        return;
    }

    private void methodTwo() {
        File file = new File("");
    }

    private static void methodThree(){
        methodThree();
    }

    public static void main(String[] args) {
        methodThree();
    }
}
