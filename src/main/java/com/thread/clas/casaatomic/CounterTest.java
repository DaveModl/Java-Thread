package com.thread.clas.casaatomic;

public class CounterTest {
    public static void main(String[] args) throws InterruptedException {
//        final Counter counter = new Counter();
//        final SyncCounter counter = new SyncCounter();
//        final LockCounter counter = new LockCounter();
//        final AtomicCounter counter = new AtomicCounter();
        final CasCounter counter = new CasCounter();
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000 ; j++) {
                    counter.add();
                }
                System.out.println("done");
            }).start();
        }
        Thread.sleep(6000L);
        //一定会小于6万
        System.out.println(counter.i);
    }
}
