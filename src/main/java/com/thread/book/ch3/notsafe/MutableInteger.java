package com.thread.book.ch3.notsafe;

import net.jcip.annotations.NotThreadSafe;

@NotThreadSafe
public class MutableInteger {
    private int value;

    public int get(){
        return this.value;
    }

    public void set(int value){
        this.value = value;
    }
}
