package com.thread.book.ch4;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class Counter {
    @GuardedBy("this") private long value = 0;

    /**
     * 读取值
     * @return
     */
    public synchronized long getValue(){
        return value;
    }

    /**
     * 写入值
     * @return
     */
    public synchronized long increment(){
        if (value == Long.MAX_VALUE){
            throw new IllegalStateException("Overflow");
        }
        return ++value;
    }
    /**
     * 两个约束对象状态的情况
     */
}
