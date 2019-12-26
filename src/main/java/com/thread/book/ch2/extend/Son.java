package com.thread.book.ch2.extend;

import com.thread.book.ch2.extend.Farther;

public class Son extends Farther {
    /**
     * 子类调用父类的同步方法证明synchronized是可重入的
     */
    @Override
    public synchronized void doSomething() {
        System.out.println(toString() + "call super doSomething...");
        super.doSomething();
    }
}
