package com.expertsoft.phoneshop.security;

import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

import com.expertsoft.phoneshop.persistence.model.UserInfo;
import com.expertsoft.phoneshop.persistence.repository.UserInfoRepository;

public class CustomH2UserDetailsManager implements UserDetailsManager {
    private String defaultAdminPassword;
    private UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userInfoRepository.getUserByLogin(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public void createUser(UserDetails user) {
        UserInfo userInfo = new UserInfo();
        userInfo.setLogin(user.getUsername());
        userInfo.setPassword(user.getPassword());
        String authorites = getAuthoritiesString(user);
        userInfo.setAuthorities(authorites);
        userInfoRepository.save(userInfo);
    }

    private String getAuthoritiesString(UserDetails user) {
        return user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }

    @Override
    public void updateUser(UserDetails user) {
        createUser(user);
    }

    @Override
    public void deleteUser(String username) {
        userInfoRepository.deleteByLogin(username);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'changePassword'");
    }

    @Override
    public boolean userExists(String username) {
        return userInfoRepository.getUserByLogin(username).isPresent();
    }

    public UserInfoRepository getUserInfoRepository() {
        return userInfoRepository;
    }

    public void setUserInfoRepository(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @PostConstruct
    public void initAdminAndGuestUser() {
        UserDetails guest = User.builder()
                .username("guest")
                .password("")
                .authorities("GUEST")
                .build();
        createUser(guest);
        UserDetails admin = User.builder()
                .username("admin")
                .password(defaultAdminPassword)
                .roles("USER", "ADMIN")
                .build();
        createUser(admin);
    }

    public String getDefaultAdminPassword() {
        return defaultAdminPassword;
    }

    public void setDefaultAdminPassword(String defaultAdminPassword) {
        this.defaultAdminPassword = defaultAdminPassword;
    }

}
