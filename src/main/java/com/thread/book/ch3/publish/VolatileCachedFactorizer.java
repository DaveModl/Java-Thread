package com.thread.book.ch3.publish;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

public class VolatileCachedFactorizer implements Servlet {
    /**将多个可变的变量保存在一个不可变对象*/
    private volatile OneValueCache cache = new OneValueCache(null,null);
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        BigInteger i = extractFromRequest(servletRequest);
        BigInteger[] factors = cache.getFactors(i);
        if (factors == null){
            factors = factor(i);
            cache = new OneValueCache(i,factors);
        }
        encodeIntoResponse(servletResponse,factors);
    }

    private void encodeIntoResponse(ServletResponse servletResponse, BigInteger[] factors) {
    }

    private BigInteger[] factor(BigInteger i) {
        return null;
    }

    private BigInteger extractFromRequest(ServletRequest servletRequest) {
        return null;
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
