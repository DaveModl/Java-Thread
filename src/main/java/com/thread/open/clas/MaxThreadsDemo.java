package com.thread.open.clas;

import java.util.concurrent.CountDownLatch;

/**
 * @author Dave
 * 最大线程数
 */
public class MaxThreadsDemo {
    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(1);
        for (int i = 0; i < 5000 ; i++) {
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            try {
                                cdl.await();
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }
                        }
                    }
            ).start();
            System.out.println("i = " + i );
        }
    }
}
