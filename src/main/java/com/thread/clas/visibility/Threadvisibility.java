package com.thread.clas.visibility;

public class Threadvisibility {
    int i = 0;
    volatile boolean isRunning = true;

    public static void main(String[] args) throws InterruptedException {
        Threadvisibility threadvisibility = new Threadvisibility();
        new Thread(() ->{
            System.out.println("hi I'm in");
            /**
             * 子线程运行代码，读取值
             *可能没有读到
             */
           while(threadvisibility.isRunning){
               threadvisibility.i++;
           }
            System.out.println(threadvisibility.i);
        }).start();

        Thread.sleep(3000L);
        /**
         * 主线程写入值
         * 1.在写内存之前会先写缓存（可见性问题，写入读不到）
         * 缓存再同步到内存
         * （缓存同步协议，保证能够读到，所以不是由于cpu缓存导致的）
         * 可能没有写入
         * 2.指令重排序
         * JIT的指令重排导致问题
         */
        threadvisibility.isRunning = false;
        System.out.println("shutdown...");
    }
}
