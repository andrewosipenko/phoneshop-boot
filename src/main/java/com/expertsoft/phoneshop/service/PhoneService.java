package com.expertsoft.phoneshop.service;

import com.expertsoft.phoneshop.persistence.model.Phone;
import com.expertsoft.phoneshop.persistence.repository.PhoneRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class PhoneService {

    @Resource
    private PhoneRepository phoneRepository;

    public Page<Phone> getPhonesPage(Pageable pageable) {
        return phoneRepository.findAll(pageable);
    }

    public Optional<Phone> getPhoneById(long id) {
        return phoneRepository.findById(id);
    }
}
