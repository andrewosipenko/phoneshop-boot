package com.expertsoft.phoneshop.filter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.expertsoft.phoneshop.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserFilterTest {
    @Mock
    private UserService userService;
    @Mock
    private ServletRequest servletRequest;
    @Mock
    private ServletResponse servletResponse;
    @Mock
    private FilterChain filterChain;

    private UserFilter userFilter = new UserFilter();

    @Test
    public void testDoFilter() throws IOException, ServletException {
        userFilter.setUserService(userService);

        userFilter.doFilter(servletRequest, servletResponse, filterChain);

        verify(servletRequest).setAttribute(eq("currentUser"), any());
        verify(userService).getCurrentUser();
    }
}
