package com.thread.clas;

public class TestStop1 {
    public static void main(String[] args) throws InterruptedException {
        ThreadStop ts = new ThreadStop();
        ts.start();
        Thread.sleep(1000L);
        //会有线程安全问题，导致i=1，j=0,违背原子性操作
//        ts.stop();
        ts.interrupt();
        while(ts.isAlive()){

        }
        ts.print();
    }
}
