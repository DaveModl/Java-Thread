package com.thread.clas;

public class FinalVar2 {
    final int x;
    int y;
    static FinalVar2 finalVar;

    /**
     * 构造版本
     * final保证可见性
     * y接受final变量的赋值也可保证可见性
     */
    public FinalVar2(){
        x = 3;
        y = x;
    }

    static void writer(){
        finalVar = new FinalVar2();
    }

    /**
     * y不一定是4
     */
    static void reader(){
        if (finalVar != null){
            int i = finalVar.x;
            int j = finalVar.y;
            System.out.println("i = " +i+",j = "+j);
        }
    }

    public static void main(String[] args) {
      new Thread(() ->{
          writer();
      }).start();

      new Thread(() ->{
          reader();
      }).start();

    }
}
