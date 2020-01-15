package com.thread.clas.casaatomic;

import java.util.concurrent.atomic.LongAccumulator;

public class Accumulator {
    public static void main(String[] args) {
        LongAccumulator accumulator = new LongAccumulator(
                /**
                 * x为当前的状态值
                 */
                (x, y) -> {
                    System.out.println("x:" + x + ",y:" + y);
                    return x + y;
                },
                0L);

        for (int i = 0; i < 3; i++) {
            //传入的y值
            accumulator.accumulate(1);
        }

        System.out.println("result=" + accumulator.get());
    }
}
