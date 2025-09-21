package com.expertsoft.phoneshop.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import com.expertsoft.phoneshop.service.UserService;

public class GithubOauth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private UserService userService;

    public GithubOauth2SuccessHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        if (authentication instanceof OAuth2AuthenticationToken) {
            userService.saveUser(((OAuth2AuthenticationToken) authentication));
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }

}
