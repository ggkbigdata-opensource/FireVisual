package com.fire.app.config;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fire.app.common.App;
import com.fire.app.domain.User;

@WebFilter(filterName="systemFilter",urlPatterns="/*")
public class SystemFilter implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        
        String servletPath = req.getServletPath();
        if (servletPath.contains("app")) {
            User user = (User) req.getSession().getAttribute(App.USER_SESSION_KEY);
            if (user ==null||"".equals(user)) {
                 res.sendRedirect("/fire-visual/");
             }else{
                 chain.doFilter(request, response);
             }
        }else{
            chain.doFilter(request, response);
        }
        
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

}