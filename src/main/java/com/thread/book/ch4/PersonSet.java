package com.thread.book.ch4;

import net.jcip.annotations.ThreadSafe;

import java.util.HashSet;
import java.util.Set;
@ThreadSafe
public class PersonSet {
    /**
     * 封闭在对象中的一个线程不安全对象
     */
    private final Set<String> mySet = new HashSet<>();

    /**
     * 加锁外部访问方法
     */
    public synchronized void addPerson(String one){
        mySet.add(one);
    }

    public synchronized  boolean containsPerson(String person){
        return mySet.contains(person);
    }
    /**
     * 若果是一个可变的类ClassXxx,在获取mySet时也需要加锁,或者加锁保证ClassXxx线程安全
     */
}
