package com.expertsoft.phoneshop.service;

import com.expertsoft.phoneshop.persistence.model.UserAccount;
import com.expertsoft.phoneshop.persistence.repository.UserAccountRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserAccountDetailsService implements UserDetailsService {

    @Resource
    private UserAccountRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount user = userRepository.findByLogin(username)
                .filter(u -> u.getPassword() != null)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return User.withUsername(user.getLogin())
                .password(user.getPassword())
                .roles(String.valueOf(user.getRole()))
                .build();
    }
}
