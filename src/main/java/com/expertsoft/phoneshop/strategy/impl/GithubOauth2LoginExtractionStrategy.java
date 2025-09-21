package com.expertsoft.phoneshop.strategy.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;

import com.expertsoft.phoneshop.strategy.LoginExtractionStrategy;

@Component
public class GithubOauth2LoginExtractionStrategy implements LoginExtractionStrategy {

    @Override
    public boolean isApplicable(Authentication authentication) {
        if (authentication instanceof OAuth2AuthenticationToken && ("github"
                .equals(((OAuth2AuthenticationToken) authentication).getAuthorizedClientRegistrationId()))) {
            return true;
        }
        return false;
    }

    @Override
    public String extractLogin(Authentication authentication) {
        return ((OAuth2AuthenticationToken) authentication).getPrincipal().getAttribute("login");
    }

}
