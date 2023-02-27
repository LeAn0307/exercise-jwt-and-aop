package com.example.exercise.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@Component
@Order(1)
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        Enumeration<String> headerNames = request.getHeaderNames();
        final String POST_MAN="Postman";
        String st = request.getHeader("user-agent");
        if (!st.contains(POST_MAN)) {
            ((HttpServletResponse) servletResponse).addHeader("name","Mi Mi");
            ((HttpServletResponse) servletResponse).setStatus(HttpServletResponse.SC_FORBIDDEN);
            ((HttpServletResponse) servletResponse).getWriter().write("Access is not allow");
            return;
        } else filterChain.doFilter(servletRequest,servletResponse);
    }
    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}