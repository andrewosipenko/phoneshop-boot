package com.expertsoft.phoneshop.strategy.impl;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.expertsoft.phoneshop.strategy.LoginExtractionStrategy;

@Component
public class PasswordAndUsernameLoginExtraction implements LoginExtractionStrategy {

    @Override
    public boolean isApplicable(Authentication authentication) {
        return authentication instanceof UsernamePasswordAuthenticationToken;
    }

    @Override
    public String extractLogin(Authentication authentication) {
        return ((UsernamePasswordAuthenticationToken) authentication).getName();
    }

}
