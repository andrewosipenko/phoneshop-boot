package com.expertsoft.phoneshop.service;

import com.expertsoft.phoneshop.persistence.model.Client;
import com.expertsoft.phoneshop.persistence.repository.ClientRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ClientService {
    @Resource
    private ClientRepository clientRepository;

    public Client registerOrUpdate(Client profile) {
        Client user = clientRepository.findByLogin(profile.getLogin())
                .orElseGet(Client::new);
        user.setLogin(profile.getLogin());
        user.setName(profile.getName());
        user.setBio(profile.getBio());
        user.setAvatarUrl(profile.getAvatarUrl());
        user.setLocation(profile.getLocation());
        user.setCompany(profile.getCompany());
        return clientRepository.save(user);
    }
}
