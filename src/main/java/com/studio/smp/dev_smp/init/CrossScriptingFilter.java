package com.studio.smp.dev_smp.init;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class CrossScriptingFilter implements Filter {

    public FilterConfig filterConfig;

    public void init(FilterConfig filterConfig)throws ServletException {
        this.filterConfig = filterConfig;
    }
    public void destory() {this.filterConfig = null;}

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException,ServletException{
        filterChain.doFilter(new RequestWrapper((HttpServletRequest) servletRequest),servletResponse);
    }
}
