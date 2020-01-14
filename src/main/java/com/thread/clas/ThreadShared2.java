package com.thread.clas;

public class ThreadShared2 {
    public static String sharedVar = "Test-Shared";

    public static void main(String[] args) {
        /**
         * 变量共享
         */
        //写入数据
        new Thread(() ->{
            try {
                while(true){
                    sharedVar = "当前时间" + String.valueOf(System.currentTimeMillis());
                    Thread.sleep(1000L);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        //读取数据
        new Thread(() ->{
            try {
                while (true){
                    Thread.sleep(1000L);
                    System.out.println(sharedVar);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
