package com.expertsoft.phoneshop.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expertsoft.phoneshop.persistence.model.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> getUserByLogin(String login);

    void deleteByLogin(String login);
}
