package com.thread.clas.casaatomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {
    AtomicInteger i = new AtomicInteger(0);
    public void add(){
       i.incrementAndGet();
    }


}
