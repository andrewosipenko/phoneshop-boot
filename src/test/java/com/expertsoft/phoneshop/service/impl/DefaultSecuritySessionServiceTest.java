package com.expertsoft.phoneshop.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.expertsoft.phoneshop.strategy.LoginExtractionStrategy;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class DefaultSecuritySessionServiceTest {
    @Mock
    private LoginExtractionStrategy loginExtractionStrategy;

    private DefaultSecuritySessionService securitySessionService = new DefaultSecuritySessionService();

    @BeforeEach
    public void setUp() {
        securitySessionService.setLoginExtractionStrategies(List.of(loginExtractionStrategy));
    }

    @Test
    void shouldReturnGuestUserWithoutAuthenfication() {
        String result = securitySessionService.extractUsername();

        assertEquals("guest", result);
    }

    @Test
    @WithMockUser
    void shouldReturnGuestUser() {
        when(loginExtractionStrategy.isApplicable(any(Authentication.class))).thenReturn(true);

        String result = securitySessionService.extractUsername();

        assertEquals("guest", result);
    }

    @Test
    @WithMockUser
    void shouldReturnUser() {
        when(loginExtractionStrategy.extractLogin(any(Authentication.class))).thenReturn("user");
        when(loginExtractionStrategy.isApplicable(any(Authentication.class))).thenReturn(true);

        String result = securitySessionService.extractUsername();

        assertEquals("user", result);
    }
}
