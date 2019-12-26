package com.thread.book.ch2.sequeue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class Sequeue {
    @GuardedBy("this") private int value;

    public synchronized  int getNext(){
        return this.value++;
    }
}
