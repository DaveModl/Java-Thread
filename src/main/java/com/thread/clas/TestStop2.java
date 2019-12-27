package com.thread.clas;

public class TestStop2 {
    /**标志位*/
    private volatile static boolean flag = true;
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            try {
                /**
                 * 循环执行某一个程序块
                 */
                while (flag){
                    System.out.println("运行中");
                    Thread.sleep(1000L);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(3000L);
        flag = false;
        System.out.println("程序运行结束");
    }
}
