package com.thread.clas;

import java.util.concurrent.locks.LockSupport;
/**
 * 生产者消费者模型
 */

/** 三种线程协作通信的方式：suspend/resume、wait/notify、park/unpark */
public class ThreadShared3 {
    /** 包子店 */
    public static Object baozidian = null;

    /** 正常的suspend/resume
     * 线程的挂起和恢复
     * */
    public void suspendResumeTest() throws Exception {
        // 启动线程
        Thread consumerThread = new Thread(() -> {
            if (baozidian == null) { // 如果没包子，则进入等待
                System.out.println("1、进入等待");
                Thread.currentThread().suspend();
            }
            System.out.println("2、买到包子，回家");
        });
        consumerThread.start();
        // 3秒之后，生产一个包子
        Thread.sleep(3000L);
        baozidian = new Object();
        consumerThread.resume();
        System.out.println("3、通知消费者");
    }

    /** 死锁的suspend/resume。 suspend并不会像wait一样释放锁，故此容易写出死锁代码 */
    public void suspendResumeDeadLockTest() throws Exception {
        // 启动线程
        Thread consumerThread = new Thread(() -> {
            if (baozidian == null) { // 如果没包子，则进入等待
                System.out.println("1、进入等待");
                // 当前线程拿到锁，然后挂起
                synchronized (this) {
                    Thread.currentThread().suspend();
                }
            }
            System.out.println("2、买到包子，回家");
        });
        consumerThread.start();
        // 3秒之后，生产一个包子
        Thread.sleep(3000L);
        baozidian = new Object();
        // 争取到锁以后，再恢复consumerThread
        // 拿不到锁
        synchronized (this) {
            consumerThread.resume();
        }
        System.out.println("3、通知消费者");
    }

    /** 导致程序永久挂起的suspend/resume
     * 先执行suspend或执行resume 否则也会死锁
     * */
    public void suspendResumeDeadLockTest2() throws Exception {
        // 启动线程
        Thread consumerThread = new Thread(() -> {
            if (baozidian == null) {
                System.out.println("1、没包子，进入等待");
                try { // 为这个线程加上一点延时
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 这里的挂起执行在resume后面
                Thread.currentThread().suspend();
            }
            System.out.println("2、买到包子，回家");
        });
        consumerThread.start();
        // 3秒之后，生产一个包子
        Thread.sleep(3000L);
        baozidian = new Object();
        // 这个时候消费者还未挂起，但是已经通知唤醒了，于是后续就永久挂起了
        consumerThread.resume();
        System.out.println("3、通知消费者");
        consumerThread.join();
    }

    /** 正常的wait/notify
     * monitor 只能是同一对象锁的持有者线程调用
     * */
    public void waitNotifyTest() throws Exception {
        // 启动线程
        new Thread(() -> {
            synchronized (this) {
                //伪唤醒,在循环中检测等待条件比较好
                while (baozidian == null) { // 如果没包子，则进入等待
                    try {
                        System.out.println("1、进入等待");
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("2、买到包子，回家");
        }).start();
        // 3秒之后，生产一个包子
        Thread.sleep(3000L);
        baozidian = new Object();
        synchronized (this) {
            this.notifyAll();
            System.out.println("3、通知消费者");
        }
    }

    /** 会导致程序永久等待的wait/notify */
    public void waitNotifyDeadLockTest() throws Exception {
        // 启动线程
        new Thread(() -> {
            if (baozidian == null) { // 如果没包子，则进入等待
                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                synchronized (this) {
                    try {
                        System.out.println("1、进入等待");
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("2、买到包子，回家");
        }).start();
        // 3秒之后，生产一个包子
        Thread.sleep(3000L);
        baozidian = new Object();
        synchronized (this) {
            //先调用notify，通知消费者，消费者还没有挂起等待
            this.notifyAll();
            System.out.println("3、通知消费者");
        }
    }

    /** 正常的park/unpark
     * 没有顺序要求
     * 不释放锁
     * */
    public void parkUnparkTest() throws Exception {
        // 启动线程
        Thread consumerThread = new Thread(() -> {
            while (baozidian == null) { // 如果没包子，则进入等待
                System.out.println("1、进入等待");
                //挂起
                LockSupport.park();
            }
            System.out.println("2、买到包子，回家");
        });
        consumerThread.start();
        // 3秒之后，生产一个包子
        Thread.sleep(3000L);
        baozidian = new Object();
        //指定线程执行
        LockSupport.unpark(consumerThread);
        System.out.println("3、通知消费者");
    }

    /** 死锁的park/unpark */
    public void parkUnparkDeadLockTest() throws Exception {
        // 启动线程
        Thread consumerThread = new Thread(() -> {
            if (baozidian == null) { // 如果没包子，则进入等待
                System.out.println("1、进入等待");
                // 当前线程拿到锁，然后挂起
                synchronized (this) {
                    LockSupport.park();
                }
            }
            System.out.println("2、买到包子，回家");
        });
        consumerThread.start();
        // 3秒之后，生产一个包子
        Thread.sleep(3000L);
        baozidian = new Object();
        // 争取到锁以后，再恢复consumerThread
        //拿不到锁
        synchronized (this) {
            LockSupport.unpark(consumerThread);
        }
        System.out.println("3、通知消费者");
    }

    public static void main(String[] args) throws Exception {
        // 对调用顺序有要求，也要开发自己注意锁的释放。这个被弃用的API， 容易死锁，也容易导致永久挂起。
//		 new ThreadShared3().suspendResumeTest();
//		 new ThreadShared3().suspendResumeDeadLockTest();
//	 new ThreadShared3().suspendResumeDeadLockTest2();

        // wait/notify要求再同步关键字里面使用，免去了死锁的困扰，但是一定要先调用wait，再调用notify，否则永久等待了
//         new ThreadShared3().waitNotifyTest();
//		 new ThreadShared3().waitNotifyDeadLockTest();

        // park/unpark没有顺序要求，但是park并不会释放锁，所有再同步代码中使用要注意
//		 new ThreadShared3().parkUnparkTest();
//        new ThreadShared3().parkUnparkDeadLockTest();

    }
}
