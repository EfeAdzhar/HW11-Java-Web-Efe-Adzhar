package com.academy.webacademy;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class LoginFilter implements Filter {

    //IN PROCESS:
    // you need to get session out of servlet and check if name attribute present.
    // in current implementation Session is not used
    // also in case of success just propagate request further. in case of error return status code. you din;t need writer in filter here

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        response.getWriter().println("Entering loginFilter");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        if (session.getAttribute("name").equals("Efe")) {
            response.getWriter().println("login is not absent");
            filterChain.doFilter(request, response);
        } else {
            response.getWriter().println("login is absent");
        }
        // here you need to propagate request further. See https://www.digitalocean.com/community/tutorials/java-servlet-filter-example-tutorial as an example RequestLoggingFilter
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}