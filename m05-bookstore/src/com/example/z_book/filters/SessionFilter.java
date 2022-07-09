package com.example.z_book.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


/*
@WebFilter(urlPatterns = {"*.do", "*.html"},
        initParams = {
                //白名单
                @WebInitParam(name = "white",
                        value = "/m05/page.do?operate=page," +
                                "/m05/user.do")
        })
*/

public class SessionFilter implements Filter {

    List<String> whiteList = null;

    @Override
    public void init(FilterConfig config) throws ServletException {
        String white = config.getInitParameter("white");
        String[] whiteArr = white.split(",");
        whiteList = Arrays.asList(whiteArr);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

//        System.out.println("request.getRequestURL() = " + request.getRequestURL());
//        System.out.println("request.getRequestURI() = " + request.getRequestURI());
//        System.out.println("request.getQueryString() = " + request.getQueryString());

        HttpSession session = request.getSession();
        Object currentUser = session.getAttribute("currentUser");

        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        String str = queryString != null ? uri + "?" + queryString : uri;

        if (currentUser == null && !whiteList.contains(str)) {
            //session中没有currentUser且不是白名单就重定向到登录页
            response.sendRedirect("page.do?operate=page");
        } else {
            filterChain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {

    }
}
