package com.thread.book.ch3.publish;

public class UnsafeState {
    private String[] states = new String[]{
            "AK","AL"
    };

    /**
     * 从一个非私有方法中返回一个引用
     * @return
     */
    public String[] getStates(){
        return states;
    }
}
