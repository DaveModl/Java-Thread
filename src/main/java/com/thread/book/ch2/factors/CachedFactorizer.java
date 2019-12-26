package com.thread.book.ch2.factors;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;
@ThreadSafe
public class CachedFactorizer implements Servlet {
   @GuardedBy("this") private BigInteger lastNumber;
   @GuardedBy("this") private BigInteger[] lastFactors;
   @GuardedBy("this") private long hits;
   @GuardedBy("this") private long cacheHits;

    public synchronized long getHits() {
        return hits;
    }

    public synchronized double getCacheHitsRatio() {
        return cacheHits / hits;
    }

    public void init(ServletConfig servletConfig) throws ServletException {

    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        BigInteger i = extractFromRequest(servletRequest);
        BigInteger[] factors = null;
        synchronized (this){
            ++hits;
            if (i.equals(lastNumber)){
                ++cacheHits;
                factors = lastFactors.clone();
            }
        }
        if (factors == null){
            factors = factor(i);
            synchronized (this){
                lastNumber = i;
                lastFactors = factors.clone();
            }
        }
        encodeIntoRespone(servletResponse,factors);
    }

    private void encodeIntoRespone(ServletResponse servletResponse, BigInteger[] factors) {
    }

    private BigInteger[] factor(BigInteger i) {
        return null;
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
