package com.liceu.notes.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "corsfilter")
public class CORSFilter implements Filter{

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        resp.addHeader("Acces-Control-Allow-Origin", "http://localhost:8080");
        resp.addHeader("Acces-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST, DELETE");
        resp.addHeader("Acces-Control-Allow-Credentials","true");
        resp.addHeader("Acces-Control-Allow-Headers", "Content-type");

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        if (req.getMethod().equals("OPTIONS")){
            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
        }
    }
}
