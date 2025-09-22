package com.expertsoft.phoneshop.service;

import com.expertsoft.phoneshop.service.dto.PhoneDto;

import org.springframework.data.domain.Page;
import java.util.Optional;

public interface PhoneService {

    public Page<PhoneDto> getPhonesPage(Integer oneBasedIndex, String sort);

    public Optional<PhoneDto> getPhoneById(Long id);

    public Page<PhoneDto> getPhonesSearchPage(Integer oneBasedIndex, String query, Double priceFrom, Double priceTo,
            String sort);
}
