package com.thread.book.ch2.factors;

import net.jcip.annotations.NotThreadSafe;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;
@NotThreadSafe
public class UnsafeCountingFactorizer implements Servlet {
    private long count = 0;
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        BigInteger i = extractFormRequest(servletRequest);
        BigInteger[] factors = factor(i);
        //统计请求数量
        ++count;
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
