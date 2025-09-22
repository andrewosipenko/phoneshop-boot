package com.expertsoft.phoneshop.security;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import com.expertsoft.phoneshop.persistence.model.UserInfo;
import com.expertsoft.phoneshop.service.UserService;

@ExtendWith(MockitoExtension.class)
public class GithubOauth2SuccessHandlerTest {
    @Mock
    private UserService userService;
    @Mock
    private OAuth2AuthenticationToken oAuth2AuthenticationToken;
    @Mock
    private Converter<OAuth2AuthenticationToken, UserInfo> userConverter;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    private GithubOauth2SuccessHandler githubOauth2SuccessHandler = new GithubOauth2SuccessHandler(userService,
            userConverter);

    @Test
    public void shouldSaveUser() throws IOException, ServletException {
        githubOauth2SuccessHandler.setUserConverter(userConverter);
        githubOauth2SuccessHandler.setUserService(userService);

        githubOauth2SuccessHandler.onAuthenticationSuccess(request, response, oAuth2AuthenticationToken);

        verify(userConverter).convert(any());
        verify(userService).saveUser(any());
    }
}
