package com.expertsoft.phoneshop.service;

import com.expertsoft.phoneshop.PhoneShopPaginationConfiguration;
import com.expertsoft.phoneshop.persistence.repository.PhoneRepository;
import com.expertsoft.phoneshop.service.convertor.PhoneConverter;
import com.expertsoft.phoneshop.service.dto.PhoneDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

import javax.annotation.Resource;

@Service
public class PhoneService {

    @Resource
    private PhoneRepository phoneRepository;

    @Resource
    private PhoneShopPaginationConfiguration paginationConfiguration;

    @Resource
    private PhoneConverter phoneConverter;

    public Page<PhoneDto> getPhonesPage(Integer oneBasedIndex) {
        Long count = phoneRepository.count();
        return phoneRepository
                .findAll(PageRequest.of(oneBasedIndex - 1,
                        Math.toIntExact(count) / paginationConfiguration.getPlpMaxPages()))
                .map(phoneConverter::convert);
    }

    public Optional<PhoneDto> getPhoneById(Long id) {
        return phoneRepository.findOneById(id)
                .map(phoneConverter::convert);
    }

    public Page<PhoneDto> getPhonesSearchPage(Integer zeroBasePage, String query, Double priceFrom, Double priceTo) {
        return null;
    }

}
