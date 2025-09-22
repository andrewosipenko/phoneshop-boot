package com.expertsoft.phoneshop.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.expertsoft.phoneshop.persistence.model.UserInfo;
import com.expertsoft.phoneshop.service.UserService;

public class GithubOauth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private UserService userService;
    private Converter<OAuth2AuthenticationToken, UserInfo> userConverter;

    public GithubOauth2SuccessHandler(UserService userService,
            Converter<OAuth2AuthenticationToken, UserInfo> userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        if (authentication instanceof OAuth2AuthenticationToken) {
            userService.saveUser(userConverter.convert((OAuth2AuthenticationToken) authentication));
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setUserConverter(Converter<OAuth2AuthenticationToken, UserInfo> userConverter) {
        this.userConverter = userConverter;
    }

}
