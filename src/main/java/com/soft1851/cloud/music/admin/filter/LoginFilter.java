package com.soft1851.cloud.music.admin.filter;

import com.soft1851.cloud.music.admin.handler.RequestWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/4/21
 * @Version 1.0
 */
@WebFilter(urlPatterns = "/*", filterName = "loginFilter")
public class LoginFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServletRequest requestWrapper = null;
        if (servletRequest instanceof HttpServletRequest) {
            requestWrapper = new RequestWrapper((HttpServletRequest) servletRequest);
        }
        if (requestWrapper == null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(requestWrapper, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
