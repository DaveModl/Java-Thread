package com.thread.book.ch2.factors;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;
@ThreadSafe
public class SynchronizedFactorizer implements Servlet {
    @GuardedBy("this") private BigInteger lastNumber;
    @GuardedBy("this") private BigInteger[] lastFactors;
    
    public void init(ServletConfig servletConfig) throws ServletException {
        
    }

    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     * 由于个Service方法加了同步锁，导致每次只有有个请求进入
     * 其他请求必须阻塞等待违背Servlet的设计原则
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    public synchronized void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        BigInteger i = extractFromRequest(servletRequest);
        if (i.equals(lastNumber)){
            encodeIntoRespone(servletResponse,lastFactors);
        }else {
            BigInteger[] factors = factor(i);
            lastNumber = i;
            lastFactors = factors;
            encodeIntoRespone(servletResponse,lastFactors);
        }
    }

    private BigInteger[] factor(BigInteger i) {
        return null;
    }

    private void encodeIntoRespone(ServletResponse servletResponse, BigInteger[] lastFactors) {
    }

    private BigInteger extractFromRequest(ServletRequest servletRequest) {
        return null;
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {

    }
}
