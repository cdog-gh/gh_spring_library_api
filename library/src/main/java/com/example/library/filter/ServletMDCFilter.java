package com.example.library.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import org.slf4j.MDC;
@Component
public class ServletMDCFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    //https://logback.qos.ch/manual/mdc.html
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        MDC.put("X-Forwarded-For", servletRequest.getRemoteAddr());
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            MDC.remove("X-Forwarded-For");
        }
    }

    @Override
    public void destroy() {

    }
}
