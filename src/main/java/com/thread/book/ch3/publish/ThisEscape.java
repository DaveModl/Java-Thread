package com.thread.book.ch3.publish;




public class ThisEscape {
    /**
     * 构造函数中隐式的this引用逸出
     * @param source
     */
    public ThisEscape(EventSource source){
        source.registerListener(
                new EventListener1(){
                    @Override
                    public void onEvent(Event1 e){
                        doSomething(e);
                    }

                    private void doSomething(Event1 e) {
                    }
                }
        );
    }

}
