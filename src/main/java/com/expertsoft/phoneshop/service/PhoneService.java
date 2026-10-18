package com.expertsoft.phoneshop.service;

import com.expertsoft.phoneshop.config.PlpConfiguration;
import com.expertsoft.phoneshop.persistence.model.Phone;
import com.expertsoft.phoneshop.persistence.repository.PhoneRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class PhoneService {

    @Resource
    private PhoneRepository phoneRepository;

    @Resource
    private PlpConfiguration plpConfiguration;

    public Page<Phone> getPhonesPage(Pageable pageable) {
        return phoneRepository.findAll(pageable);
    }

    public Optional<Phone> getPhoneById(long id) {
        return phoneRepository.findById(id);
    }

    public Page<Phone> getPhonesSearchPage(int page, String query, BigDecimal priceFrom, BigDecimal priceTo) {
        Pageable pageable = PageRequest.of(page - 1, plpConfiguration.getPlpMaxProducts());
        String normalized = StringUtils.hasText(query) ? query.trim() : null;
        return phoneRepository.search(normalized, priceFrom, priceTo, pageable);
    }
}
