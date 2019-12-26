package com.thread.open.clas;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author Dave
 * 自己实现线程池
 */
public class MyThreadPool {
    //仓库
    private BlockingDeque<Runnable> taskQueue;

    //线程集合
    private List<Worker> workers;

    private volatile boolean working = true;

    public MyThreadPool(int poolSize, int taskQueueSize) {
        if (poolSize <= 0 || taskQueueSize <= 0) {
            throw new IllegalArgumentException("Argument Error");
        }

        //初始化任务队列
        this.taskQueue = new LinkedBlockingDeque<>(taskQueueSize);

        //创建线程集合
        this.workers = new ArrayList<>();

        //初始化工作线程
        for (int i = 0; i < poolSize; i++) {
            Worker w = new Worker(this);
            this.workers.add(w);
            w.start();
        }
    }

    public void shutdown() {
        if (this.working) {
            this.working = false;
            //如果工作线程处于阻塞状态则唤醒
            for (Thread t : this.workers) {
                if (t.getState().equals(Thread.State.BLOCKED) || t.getState().equals(Thread.State.WAITING)) {
                    //中断阻塞状态
                    t.interrupt();
                }
            }
        }
    }

    public boolean submit(Runnable task) {
        return this.taskQueue.offer(task);
    }


    private class Worker extends Thread {
        private MyThreadPool pool;

        public Worker(MyThreadPool pool) {
            super();
            this.pool = pool;
        }

        @Override
        public void run() {
            //任务计数器
            int taskCount = 0;

            //从任务仓库取任务执行
            while (this.pool.working || this.pool.taskQueue.size() > 0) {
                Runnable task = null;
                try {
                    if (this.pool.working) {
                        task = this.pool.taskQueue.take();
                    } else {
                        task = this.pool.taskQueue.poll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (task != null) {
                    try {
                        task.run();
                        System.out.println(Thread.currentThread().getName() + " finish");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println(Thread.currentThread().getName() + " over");
        }
    }

    public static void main(String[] args) {
        MyThreadPool pool = new MyThreadPool(3, 5);

        for (int i = 0; i < 5; i++) {
            pool.submit(() -> {
                System.out.println("Task start...");
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }
        pool.shutdown();
    }
}
