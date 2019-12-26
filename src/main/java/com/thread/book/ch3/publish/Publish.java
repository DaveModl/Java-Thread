package com.thread.book.ch3.publish;

import java.util.HashSet;
import java.util.Set;

public class Publish {
    public static Set<String> mySet;

    /**
     * 将一个对象保存在一个共有静态变量
     * 在mySet发布的同时，String对象也发布了
     */
    public void initialize(){
        mySet = new HashSet<>();
    }
}
