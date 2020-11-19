package com.liceu.notes.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URL;

@WebFilter(filterName = "logoutfilter")
public class logOutFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        URL url = new URL(((HttpServletRequest) req).getRequestURL().toString());
        if (req.getParameter("logout") != null){
            HttpSession session = ((HttpServletRequest) req).getSession();
            session.invalidate();
            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/login.jsp");
            dispatcher.forward(req, resp);
            return;
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher(url.getPath());
        dispatcher.forward(req, resp);
    }

    public void destroy() {

    }
}
