package com.thread.clas.casaatomic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockCounter {
    volatile int i = 0;
    //互斥锁
    Lock lock = new ReentrantLock();
    public void add(){
        lock.lock();
        i++;
        lock.unlock();
    }
}
