package com.thread.clas.casaatomic;

public class Counter {
    volatile int i = 0;
    public void add(){
        /**
         * 非原子操作,读取i的值，进行+1操作，写入i
         * 先后时序加载的数据失效
         * ---------在方法栈帧中-----------------
         * 1.getfield --- 加载字段到操作数栈
         * 2.iconst_1 --- 放入常量1
         * 3.iadd --- 加1操作
         * 4.putfeild --- 将操作数栈中的值写回堆内存
         *
         * -------------与可见性的差异---------------------
         * 先写再读！！！！
         * 写之前就读了，就读到了
         */
        i++;
    }
}
