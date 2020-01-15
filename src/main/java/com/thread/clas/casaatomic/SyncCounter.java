package com.thread.clas.casaatomic;

public class SyncCounter {
    volatile int i = 0;
    public synchronized void add(){
        i++;
    }
}
