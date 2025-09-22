package com.expertsoft.phoneshop;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.expertsoft.phoneshop.persistence.model.UserInfo;
import com.expertsoft.phoneshop.persistence.repository.UserInfoRepository;
import com.expertsoft.phoneshop.security.CustomH2UserDetailsManager;
import com.expertsoft.phoneshop.security.GithubOauth2SuccessHandler;
import com.expertsoft.phoneshop.service.UserService;

@Configuration
public class SecurityConfig {
    @Resource
    private UserInfoRepository userInfoRepository;
    @Resource
    private Converter<OAuth2AuthenticationToken, UserInfo> userConverter;

    @Resource
    private UserService userService;

    @Bean
    public SecurityFilterChain phoneSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorize -> authorize
                .antMatchers("/phones").permitAll()
                .antMatchers("/phones/**").hasRole("USER")
                .antMatchers("/admin/**").hasRole("ADMIN"))
                .formLogin(Customizer.withDefaults())
                .logout(logout -> logout.logoutSuccessUrl("/phones").permitAll())
                .oauth2Login(oauth -> oauth.successHandler(githubOauth2AuthenticationSuccessHandler()));

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler githubOauth2AuthenticationSuccessHandler() {
        return new GithubOauth2SuccessHandler(userService, userConverter);
    }

    @Bean
    @ConditionalOnProperty(name = "phoneshop.admin.user.enabled", havingValue = "true")
    public UserDetailsManager users(UserInfoRepository userInfoRepository,
            @Value(value = "${phoneshop.admin.user.password}") String defaultPassword) {
        CustomH2UserDetailsManager detailsManager = new CustomH2UserDetailsManager();
        detailsManager.setDefaultAdminPassword(defaultPassword);
        detailsManager.setUserInfoRepository(userInfoRepository);
        return detailsManager;
    }
}
