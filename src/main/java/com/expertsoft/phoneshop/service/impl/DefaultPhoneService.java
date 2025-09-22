package com.expertsoft.phoneshop.service.impl;

import com.expertsoft.phoneshop.persistence.model.Phone;
import com.expertsoft.phoneshop.persistence.repository.PhoneRepository;
import com.expertsoft.phoneshop.service.PaginationService;
import com.expertsoft.phoneshop.service.PhoneService;
import com.expertsoft.phoneshop.service.dto.PhoneDto;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

import javax.annotation.Resource;

@Service
public class DefaultPhoneService implements PhoneService {
    @Resource
    private PhoneRepository phoneRepository;
    @Resource
    private Converter<Phone, PhoneDto> phoneConverter;
    @Resource
    private PaginationService paginationService;

    public Page<PhoneDto> getPhonesPage(Integer oneBasedIndex, String sort) {
        return phoneRepository
                .findAll(paginationService.getPageRequest(oneBasedIndex, sort))
                .map(phoneConverter::convert);
    }

    public Optional<PhoneDto> getPhoneById(Long id) {
        return phoneRepository.findOneById(id)
                .map(phoneConverter::convert);
    }

    public Page<PhoneDto> getPhonesSearchPage(Integer oneBasedIndex, String query, Double priceFrom, Double priceTo,
            String sort) {
        Page<Phone> result;
        if (query == null) {
            result = phoneRepository.findAllByPriceBetween(getPriceFrom(priceFrom), getPriceTo(priceTo),
                    paginationService.getPageRequest(oneBasedIndex, sort));
        } else {
            result = phoneRepository.findAllByBrandContainingAndModelContainingAndPriceBetween(query,
                    getPriceFrom(priceFrom), getPriceTo(priceTo),
                    paginationService.getPageRequest(oneBasedIndex, sort));
        }
        return result.map(phoneConverter::convert);
    }

    private BigDecimal getPriceFrom(Double priceFrom) {
        return priceFrom == null ? BigDecimal.ZERO : BigDecimal.valueOf(priceFrom);
    }

    private BigDecimal getPriceTo(Double priceTo) {
        return priceTo == null ? BigDecimal.valueOf(Double.MAX_VALUE) : BigDecimal.valueOf(priceTo);
    }

    public void setPhoneRepository(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    public void setPhoneConverter(Converter<Phone, PhoneDto> phoneConverter) {
        this.phoneConverter = phoneConverter;
    }

    public void setPaginationService(PaginationService paginationService) {
        this.paginationService = paginationService;
    }
}
