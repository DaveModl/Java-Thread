package com.thread.clas;

public class FinalVar {
    final int x;
    int y;
    static  FinalVar finalVar;

    /**
     * 构造版本
     * final保证可见性
     */
    public FinalVar(){
        x = 3;
        y = 4;
    }

    static void writer(){
        finalVar = new FinalVar();
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
