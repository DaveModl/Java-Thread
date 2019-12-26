package com.thread.book.ch4;

import net.jcip.annotations.GuardedBy;

public class PrivateLock {
    private final Object myLock = new Object();
    @GuardedBy("myLock") Person person;

    void someMethod(){
        /**
         * 其他对象不能或者私有锁保证线程安全。可以通过共有方法访问锁
         */
        synchronized (myLock){
            //modify Person
        }
    }

}
