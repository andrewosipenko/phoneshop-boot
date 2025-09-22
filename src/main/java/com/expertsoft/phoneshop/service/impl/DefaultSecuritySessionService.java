package com.expertsoft.phoneshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.expertsoft.phoneshop.service.SecuritySessionService;
import com.expertsoft.phoneshop.strategy.LoginExtractionStrategy;

@Service
public class DefaultSecuritySessionService implements SecuritySessionService {
    private static final String GUEST = "guest";

    @Resource
    private List<LoginExtractionStrategy> loginExtractionStrategies;

    @Override
    public String extractUsername() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            return getLogin(authentication);
        }

        return GUEST;
    }

    private String getLogin(Authentication authentication) {
        return loginExtractionStrategies.stream()
                .filter(strategy -> strategy.isApplicable(authentication))
                .findFirst()
                .map(strategy -> strategy.extractLogin(authentication))
                .orElse(GUEST);
    }

    public void setLoginExtractionStrategies(List<LoginExtractionStrategy> loginExtractionStrategies) {
        this.loginExtractionStrategies = loginExtractionStrategies;
    }

}
