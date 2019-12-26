package com.thread.book.ch3.notsafe;

public class Novisibility {
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread{
        @Override
        public void run() {
            /**
             * 可能存在指令重排序问题：
             * 1.读不到ready的值,则程序卡死，看不到number的输出
             * 2.读到了ready的值，但是读不到number的值
             */
            while(!ready) {
                //暂停当前线程
                Thread.yield();
            }
                System.out.println(number);
            }
        }

    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }
    }
