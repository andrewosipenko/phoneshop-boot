package com.expertsoft.phoneshop.config;

import com.expertsoft.phoneshop.service.GithubOAuth2UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.Resource;

import static com.expertsoft.phoneshop.PhoneShopConstants.LOGIN_PATH;
import static com.expertsoft.phoneshop.PhoneShopConstants.PHONES_PATH;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Resource
    private GithubOAuth2UserService githubOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(auth -> auth
                        .mvcMatchers(PHONES_PATH + "/{phoneId}").authenticated()
                        .anyRequest().permitAll())
                .oauth2Login(oauth -> oauth
                        .loginPage(LOGIN_PATH)
                        .defaultSuccessUrl(PHONES_PATH)
                        .userInfoEndpoint(info -> info.userService(githubOAuth2UserService)))
                .logout(logout -> logout.logoutSuccessUrl(PHONES_PATH));
    }
}
