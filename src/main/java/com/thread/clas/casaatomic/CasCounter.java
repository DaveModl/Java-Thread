package com.thread.clas.casaatomic;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class CasCounter {
    volatile int i = 0;
    //取得一个Unsafe对象,修改变量的值
    /**
     * 对象内存区 --- 获取字段的offset --- 映射到内存地址
     */
    private static Unsafe unsafe = null;
    private static long varOffset;
    static {
        //反射获取Unsafe
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);

            Field classField = CasCounter.class.getDeclaredField("i");
            //获取变量对应的偏移量
            varOffset = unsafe.objectFieldOffset(classField);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public void add(){
        for (;;) {
            int cur = unsafe.getIntVolatile(this, varOffset);
            //cas操作
            if (unsafe.compareAndSwapInt(this, varOffset, cur, cur + 1)){
                break;
            }
        }
    }
}
