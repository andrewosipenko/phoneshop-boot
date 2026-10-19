package com.expertsoft.phoneshop.config;

import com.expertsoft.phoneshop.service.GithubOAuth2UserService;
import com.expertsoft.phoneshop.service.UserAccountDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

import static com.expertsoft.phoneshop.PhoneShopConstants.*;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Resource
    private GithubOAuth2UserService githubOAuth2UserService;
    @Resource
    private UserAccountDetailsService userAccountDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userAccountDetailsService).passwordEncoder(passwordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(auth -> auth
                        .mvcMatchers(ADMIN_PATH, ADMIN_PATH + "/**").hasRole("ADMIN")
                        .mvcMatchers(PHONES_PATH + "/{phoneId}").authenticated()
                        .anyRequest().permitAll())
                .formLogin(form -> form
                        .loginPage(LOGIN_PATH)
                        .defaultSuccessUrl(PHONES_PATH))
                .oauth2Login(oauth -> oauth
                        .loginPage(LOGIN_PATH)
                        .defaultSuccessUrl(PHONES_PATH)
                        .userInfoEndpoint(info -> info.userService(githubOAuth2UserService)))
                .logout(logout -> logout.logoutSuccessUrl(PHONES_PATH));
    }
}
