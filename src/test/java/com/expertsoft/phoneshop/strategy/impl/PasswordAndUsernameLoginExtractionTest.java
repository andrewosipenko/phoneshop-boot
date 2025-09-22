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

@ExtendWith(MockitoExtension.class)
public class PasswordAndUsernameLoginExtractionTest {
    private static final String USERNAME = "admin";
    @Mock
    private UsernamePasswordAuthenticationToken usernamePasswordToken;
    @Mock
    private OAuth2AuthenticationToken oauth2token;

    private PasswordAndUsernameLoginExtraction instance = new PasswordAndUsernameLoginExtraction();

    @Test
    public void shouldExtractName() {
        when(usernamePasswordToken.getName()).thenReturn(USERNAME);

        String result = instance.extractLogin(usernamePasswordToken);

        assertEquals(USERNAME, result);
    }

    @Test
    public void shouldBeApplicableForUsernamePasswordAuthToken() {
        boolean result = instance.isApplicable(usernamePasswordToken);

        assertTrue(result);
    }

    @Test
    void shouldBeNotApplicableForUsernamePasswordAuthToken() {
        boolean result = instance.isApplicable(oauth2token);

        assertFalse(result);
    }
}
