package com.expertsoft.phoneshop.service;

import com.expertsoft.phoneshop.config.PagesConfiguration;
import com.expertsoft.phoneshop.data.SortData;
import com.expertsoft.phoneshop.persistence.model.Phone;
import com.expertsoft.phoneshop.persistence.repository.PhoneRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private PagesConfiguration pagesConfiguration;

    @Resource
    private SortService sortService;

    public Page<Phone> getPhonesPage(int page, SortData sortData) {
        return phoneRepository.findAll(createPageable(page, sortData));
    }

    public Optional<Phone> getPhoneById(long id) {
        return phoneRepository.findById(id);
    }

    public Page<Phone> getPhonesSearchPage(int page, String query, BigDecimal priceFrom, BigDecimal priceTo, SortData sortData) {
        String normalized = StringUtils.hasText(query) ? query.trim() : null;
        return phoneRepository.search(normalized, priceFrom, priceTo, createPageable(page, sortData));
    }

    private Pageable createPageable(int page, SortData sortData) {
        return PageRequest.of(Math.max(1, page) - 1, pagesConfiguration.getPlpMaxProducts(), sortService.createSort(sortData));
    }
}
