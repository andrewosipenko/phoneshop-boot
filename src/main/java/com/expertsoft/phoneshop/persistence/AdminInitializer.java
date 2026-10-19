package com.expertsoft.phoneshop.persistence;

import com.expertsoft.phoneshop.persistence.model.Role;
import com.expertsoft.phoneshop.persistence.model.UserAccount;
import com.expertsoft.phoneshop.persistence.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@ConditionalOnProperty(name = "phoneshop.admin.enabled", havingValue = "true")
public class AdminInitializer implements ApplicationRunner {

    @Value("${phoneshop.admin.login:admin}")
    private String username;

    @Value("${phoneshop.admin.password}")
    private String password;

    @Resource
    private UserAccountRepository userRepository;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) {
        if (userRepository.findByLogin(username).isEmpty()) {
            UserAccount admin = new UserAccount();
            admin.setLogin(username);
            admin.setPassword(passwordEncoder.encode(password));
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);
        }
    }
}
