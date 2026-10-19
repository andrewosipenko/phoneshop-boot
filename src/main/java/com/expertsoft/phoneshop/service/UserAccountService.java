package com.expertsoft.phoneshop.service;

import com.expertsoft.phoneshop.config.PagesConfiguration;
import com.expertsoft.phoneshop.persistence.model.Role;
import com.expertsoft.phoneshop.persistence.model.UserAccount;
import com.expertsoft.phoneshop.persistence.repository.UserAccountRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class UserAccountService {
    @Resource
    private UserAccountRepository userAccountRepository;

    @Resource
    private PagesConfiguration pagesConfiguration;

    public UserAccount registerOrUpdate(UserAccount profile) {
        return userAccountRepository.findByLogin(profile.getLogin())
                .map(existingUser -> updateExistingUser(existingUser, profile))
                .orElseGet(() -> createUser(profile));
    }

    private UserAccount updateExistingUser(
            UserAccount user,
            UserAccount profile) {

        if (user.getRole() == Role.ADMIN) {
            return user;
        }

        updateProfile(user, profile);
        return userAccountRepository.save(user);
    }

    private UserAccount createUser(UserAccount profile) {
        UserAccount user = new UserAccount();
        user.setRegisteredAt(LocalDateTime.now());
        user.setRole(Role.USER);

        updateProfile(profile, user);

        return userAccountRepository.save(user);
    }

    private void updateProfile(UserAccount source, UserAccount target) {
        target.setLogin(source.getLogin());
        target.setName(source.getName());
        target.setBio(source.getBio());
        target.setAvatarUrl(source.getAvatarUrl());
        target.setLocation(source.getLocation());
        target.setCompany(source.getCompany());
    }

    public Page<UserAccount> getUsersPage(int page) {
        return userAccountRepository.findAll(createPageable(page));
    }


    private Pageable createPageable(int page) {
        return PageRequest.of(Math.max(1, page) - 1, pagesConfiguration.getMaxUsers());
    }
}
