package com.thread.book.ch2.lazyload;

import net.jcip.annotations.NotThreadSafe;

@NotThreadSafe
public class LazyInitRace {
    private LazyInitRace instance = null;

    /**
     * 会因为延迟加载，多线程时序问题导致创建多个对象
     * @return
     */
    public LazyInitRace getInstance() {
        if (instance == null){
            instance = new LazyInitRace();
        }
        return instance;
    }
}
