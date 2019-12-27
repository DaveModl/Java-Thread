package com.thread.clas;

public class ThreadStop extends Thread {
    private int i = 0,j = 0;

    @Override
    public void run() {
        //增加同步锁，保证变量自增的原子性
        synchronized (this) {
            ++i;
            try {
                //模式业务时间消耗
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ++j;
        }
    }
        public void print(){
            System.out.println("i = " + i + ", j = " + j);
        }
    }
