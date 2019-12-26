package com.thread.book.ch3.safe;

import com.thread.book.ch3.publish.Event1;
import com.thread.book.ch3.publish.EventListener1;
import com.thread.book.ch3.publish.EventSource;

public class SafeListener {
    private final EventListener1 listener1;

    /**
     * 私有构造器，通过工厂模式创建对象
     */
    private SafeListener() {
        this.listener1 = new EventListener1(){
            @Override
            public void onEvent(Event1 e) {
                doSometing(e);
            }
        };
    }

    private void doSometing(Event1 e) {
    }

    public static SafeListener newInstance(EventSource source){
        SafeListener safeListener = new SafeListener();
        source.registerListener(safeListener.listener1);
        return safeListener;
    }
}
