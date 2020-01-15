package com.thread.clas.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 线程池 --- 生产者消费者模型
 */
public class MyThreadPool2 {
    //阻塞队列实现生产者消费者模型
    BlockingDeque<Runnable> workQueue;
    //内部工作线程
    List<WorkThread> threads = new ArrayList<>();
    //初始化构造方法
MyThreadPool2(int poolSize,BlockingDeque<Runnable>workQueue){
this.workQueue = workQueue;
    for (int i = 0; i < poolSize ; i++) {
        WorkThread work = new WorkThread();
        work.start();
        threads.add(work);
    }
}

//提交任务
    void execute(Runnable command) throws InterruptedException {
    workQueue.put(command);
    }

    class WorkThread extends Thread{
        @Override
        public void run() {
            //循环取任务执行
            while (true){
                try {
                    Runnable task = workQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingDeque<Runnable> workQueue = new LinkedBlockingDeque<>(2);
        MyThreadPool2 threadPool2 = new MyThreadPool2(10,workQueue);
        threadPool2.execute(()->{
            System.out.println("hello");
        });
    }

}
