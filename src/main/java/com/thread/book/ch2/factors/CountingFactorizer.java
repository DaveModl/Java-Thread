package com.thread.book.ch2.factors;

import net.jcip.annotations.ThreadSafe;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

@ThreadSafe
public class CountingFactorizer implements Servlet {
    /**
     * 使用原子类型变量保证原子性操作
     */
    private final AtomicLong count = new AtomicLong(0);

    public long getCount(){
        return count.get();
    }
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        BigInteger i = extractFormRequest(servletRequest);
        BigInteger[] factors = factor(i);
        //原子性的计数器
        count.incrementAndGet();
        encodeIntoRespone(servletResponse,factors);
    }

    private void encodeIntoRespone(ServletResponse servletResponse, BigInteger[] factors) {
    }

    private BigInteger[] factor(BigInteger i) {
        return null;
    }

    private BigInteger extractFormRequest(ServletRequest servletRequest) {
        return null;
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {

    }
}
