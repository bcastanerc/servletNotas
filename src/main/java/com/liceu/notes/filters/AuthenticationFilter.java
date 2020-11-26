package com.liceu.notes.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "authenticationfilter")
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) req).getSession();
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        Object o = session.getAttribute("user_id");
        if (o != null) {
            filterChain.doFilter(req,resp);
        return;
        }
        resp.sendRedirect(((HttpServletRequest) req).getContextPath() + "/login");
    }

    public void destroy() {

    }
}
