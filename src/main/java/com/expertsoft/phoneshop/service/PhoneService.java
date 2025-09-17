package com.expertsoft.phoneshop.service;

import com.expertsoft.phoneshop.PhoneShopPaginationConfiguration;
import com.expertsoft.phoneshop.persistence.model.Phone;
import com.expertsoft.phoneshop.persistence.repository.PhoneRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PhoneService {

    @Resource
    private PhoneRepository phoneRepository;

    @Resource
    private PhoneShopPaginationConfiguration paginationConfiguration;

    public Page<Phone> getPhonesPage(Integer zeroBasedPage) {
        return phoneRepository.findAll(PageRequest.of(zeroBasedPage, paginationConfiguration.getDefaultPageSize()));
    }
}
