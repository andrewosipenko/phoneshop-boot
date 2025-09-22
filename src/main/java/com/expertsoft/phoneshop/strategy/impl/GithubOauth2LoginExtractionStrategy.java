package com.expertsoft.phoneshop.strategy.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;

import com.expertsoft.phoneshop.strategy.LoginExtractionStrategy;

@Component
public class GithubOauth2LoginExtractionStrategy implements LoginExtractionStrategy {

    private static final String CLIENT_ID_GITHUB = "github";

    @Override
    public boolean isApplicable(Authentication authentication) {
        return authentication instanceof OAuth2AuthenticationToken && CLIENT_ID_GITHUB
                .equals(((OAuth2AuthenticationToken) authentication).getAuthorizedClientRegistrationId());
    }

    @Override
    public String extractLogin(Authentication authentication) {
        return ((OAuth2AuthenticationToken) authentication).getPrincipal().getAttribute("login");
    }

}
