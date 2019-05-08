package security;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthhenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // get http request and response
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        // set isAuthenticated default false
        boolean isAuthenticated = false;
        String level = "";
        // get all cookies from request
        Cookie[] cookies = httpServletRequest.getCookies();
        // check cookies
        if (cookies != null) {
            for (Cookie cokie: cookies) {
                // check cookie user and set isAuthenticated is true if there is user cookie
                if (cokie.getName().equals("user")){
                    isAuthenticated = true;
                }
                if (cokie.getName().equals("page")){
                    level = cokie.getValue();
                }
            }
        }

        // check request url
        if (httpServletRequest.getRequestURL().toString().endsWith("/login")) {

            if (isAuthenticated) {
                if (level.equalsIgnoreCase("customer")){
                    httpServletResponse.sendRedirect("/customer");
                } else if (level.equalsIgnoreCase("wallet")){
                    httpServletResponse.sendRedirect("/wallet");
                }
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }

        } else if (httpServletRequest.getRequestURL().toString().endsWith("/customer")) {

            if (isAuthenticated) {
                if (!level.equalsIgnoreCase("customer")){
                    httpServletResponse.sendRedirect("/403");
                } else {
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            } else {
                httpServletResponse.sendRedirect("/403");
            }

        } else if (httpServletRequest.getRequestURL().toString().endsWith("/wallet")) {

            if (isAuthenticated) {
                if (!level.equalsIgnoreCase("customer")){
                    httpServletResponse.sendRedirect("/403");
                } else {
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            } else {
                httpServletResponse.sendRedirect("/403");
            }

        } else {

            if (isAuthenticated) {
                // allow acces siswa page or other, if user is loged in
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                httpServletResponse.sendRedirect("/login");
            }
        }

    }

    @Override
    public void destroy() {

    }
}
