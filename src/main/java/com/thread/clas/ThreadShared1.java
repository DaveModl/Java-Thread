package com.thread.clas;

import java.nio.file.Files;
import java.nio.file.Paths;

public class ThreadShared1 {
    public static void main(String[] args) {
        /**
         * 线程通过文件共享的示例
         */
        //线程1 --->写入数据
        new Thread (() ->{
            try {
                while(true){
                    Files.write(Paths.get("logger.log"),
                            ("当前时间" + String.valueOf(System.currentTimeMillis())).getBytes());
                    Thread.sleep(1000L);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();

        //线程2 --->读取数据
        new Thread(() ->{
            try {
                while(true){
                    Thread.sleep(1000L);
                    byte[] allBytes = Files.readAllBytes(Paths.get("logger.log"));
                    System.out.println(new String(allBytes));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();


    }
}
