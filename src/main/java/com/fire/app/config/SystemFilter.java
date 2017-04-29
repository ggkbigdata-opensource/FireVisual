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

import org.springframework.beans.factory.annotation.Autowired;

import com.fire.app.common.App;
import com.fire.app.domain.User;
import com.fire.app.domain.UserRepository;

@WebFilter(filterName="systemFilter",value="/app/**")
public class SystemFilter implements Filter {

    @Autowired
    UserRepository userRepository;
    
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
     // 设置字符集  
        req.setCharacterEncoding("utf-8");  
        String servletPath = req.getServletPath();
        User user = (User) req.getSession().getAttribute(App.USER_SESSION_KEY);
        if (servletPath.contains("app")) {
            if (user ==null||"".equals(user)) {
                 res.sendRedirect("/fire-visual/");
                 return ;
             }
            User dbUser = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
            if (!user.getToken().equals(dbUser.getToken())) {
                res.sendRedirect("/fire-visual/");
                return ;
            }
        }
        chain.doFilter(request, response);
        
        
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

}