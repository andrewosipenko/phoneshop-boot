package com.expertsoft.phoneshop;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.expertsoft.phoneshop.security.GithubOauth2SuccessHandler;
import com.expertsoft.phoneshop.service.UserService;

@Configuration
public class SecurityConfig {
    @Resource
    private UserService userService;

    @Bean
    @Order(2)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorize -> authorize
                .antMatchers("/phones").permitAll()
                .antMatchers("/phones/**").hasRole("USER"))
                .oauth2Login(oauth -> oauth.successHandler(githubOauth2AuthenticationSuccessHandler()));

        return http.build();
    }

    @Bean
    @Order(1)
    public SecurityFilterChain adminSecurityFilterChain(HttpSecurity http) throws Exception {
        http.antMatcher("/admin/**").authorizeRequests(authorize -> authorize.antMatchers("/admin/**").hasRole("ADMIN"))
                .formLogin(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler githubOauth2AuthenticationSuccessHandler() {
        return new GithubOauth2SuccessHandler(userService);
    }

}
