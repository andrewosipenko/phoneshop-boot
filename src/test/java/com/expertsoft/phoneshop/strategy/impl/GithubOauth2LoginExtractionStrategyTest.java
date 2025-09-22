package com.expertsoft.phoneshop.strategy.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

@ExtendWith(MockitoExtension.class)
public class GithubOauth2LoginExtractionStrategyTest {
    private static final String GITHUB = "github";
    private static final String USERNAME = "admin";
    private static final String LOGIN = "login";
    @Mock
    private UsernamePasswordAuthenticationToken usernamePasswordToken;
    @Mock
    private OAuth2AuthenticationToken oauth2token;
    @Mock
    private OAuth2User oAuth2User;

    private GithubOauth2LoginExtractionStrategy instance = new GithubOauth2LoginExtractionStrategy();

    @Test
    public void shouldExtractName() {
        when(oauth2token.getPrincipal()).thenReturn(oAuth2User);
        when(oAuth2User.getAttribute(LOGIN)).thenReturn(USERNAME);

        String result = instance.extractLogin(oauth2token);

        assertEquals(USERNAME, result);
    }

    @Test
    public void shouldBeApplicableForOAuth2AuthenticationToken() {
        when(oauth2token.getAuthorizedClientRegistrationId()).thenReturn(GITHUB);

        boolean result = instance.isApplicable(oauth2token);

        assertTrue(result);
    }

    @Test
    void shouldBeNotApplicableForUsernameOAuth2AuthenticationToken() {
        boolean result = instance.isApplicable(usernamePasswordToken);

        assertFalse(result);
    }
}
