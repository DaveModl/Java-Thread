package com.thread.clas;

public class ThreadVisibility2 {
    private long count = 0;
    private void add10k(){
        int idx = 0;
        while (++idx < 10000){
            count = count + 1;
        }
    }
    public static long cal() throws InterruptedException {
        final ThreadVisibility2 threadVisibility2 = new ThreadVisibility2();

       Thread t1 =  new Thread(() ->{
            threadVisibility2.add10k();
        });

       Thread t2 = new Thread(() ->{
           threadVisibility2.add10k();
       });

       t1.start();
       t2.start();

       t1.join();
       t2.join();

       return threadVisibility2.count;
    }

    public static void main(String[] args) throws InterruptedException {
        /**
         * 缓存导致的可见性问题？
         * 预期值是20k
         * 实际值10k-20k之间
         */
        System.out.println(cal());
    }
}
