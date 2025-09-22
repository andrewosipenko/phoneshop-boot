package com.expertsoft.phoneshop.strategy;

import org.springframework.security.core.Authentication;

public interface LoginExtractionStrategy {
    boolean isApplicable(Authentication authentication);

    String extractLogin(Authentication authentication);
}
