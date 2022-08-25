package com.academy.webacademy;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        response.getWriter().println("Entering loginFilter");
        if (request.getAttribute("name").equals("Efe")) {
            response.getWriter().println("login is not absent");
            filterChain.doFilter(request, response);
        } else {
            response.getWriter().println("login is absent");
        }
    }

    public void session(HttpSession session, HttpServletResponse response) throws IOException {
        session.getAttribute("name");
        if (session.isNew()) {
            response.getWriter().println
                    ("Session is new. Method called from filter: "
                            + session.getAttribute("name"));
        } else {
            response.getWriter().println("login is absent");
        }
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