package com.expertsoft.phoneshop.filter;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Component;

import com.expertsoft.phoneshop.service.UserService;

@Component
public class UserFilter implements Filter {
    private static final String CURRENT_USER = "currentUser";
    @Resource
    private UserService userService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        request.setAttribute(CURRENT_USER, userService.getCurrentUser());
        chain.doFilter(request, response);
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
