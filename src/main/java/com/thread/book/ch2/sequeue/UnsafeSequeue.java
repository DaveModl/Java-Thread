package com.thread.book.ch2.sequeue;

import net.jcip.annotations.NotThreadSafe;

@NotThreadSafe
public class UnsafeSequeue {
    private int value;

    /**
     * 因为指令重排序，两个或多个线程交替执行导致数据不一致
     * @return
     */
    public int getNext(){
        return this.value++;
    }
}
