package com.thread.book.ch2.factors;

import net.jcip.annotations.NotThreadSafe;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

@NotThreadSafe
public class UnsafeCachingFactorizer implements Servlet {
    private final AtomicReference<BigInteger> lastNumber = new AtomicReference<BigInteger>();
    private final AtomicReference<BigInteger[]> lastFactors = new AtomicReference<BigInteger[]>();

    public void init(ServletConfig servletConfig) throws ServletException {

    }

    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     * 虽然使用了原子类变量，但是无法保证执行过程中的每一步都是原子的，变量直接存在相互约束
     * lastFactors的所有因数积 = lastNumber
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        BigInteger i = extractFormRequest(servletRequest);
        if (i.equals(lastNumber.get())){
            encodeIntoRespone(servletResponse,lastFactors.get());
        }else {
            BigInteger[] factors = factor(i);
            lastNumber.set(i);
            lastFactors.set(factors);
            encodeIntoRespone(servletResponse,factors);
        }
    }

    private BigInteger[] factor(BigInteger i) {
        return null;
    }

    private void encodeIntoRespone(ServletResponse servletResponse, BigInteger[] bigIntegers) {
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
