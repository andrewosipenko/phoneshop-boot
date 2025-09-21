package com.expertsoft.phoneshop.service;

import com.expertsoft.phoneshop.PhoneShopPaginationConfiguration;
import com.expertsoft.phoneshop.persistence.model.Phone;
import com.expertsoft.phoneshop.persistence.repository.PhoneRepository;
import com.expertsoft.phoneshop.service.dto.PhoneDto;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

import javax.annotation.Resource;

@Service
public class PhoneService {

    @Resource
    private PhoneRepository phoneRepository;

    @Resource
    private PhoneShopPaginationConfiguration paginationConfiguration;

    @Resource
    private Converter<Phone, PhoneDto> phoneConverter;

    @Resource
    private Converter<String, Sort> sortConvertor;

    public Page<PhoneDto> getPhonesPage(Integer oneBasedIndex, String sort) {
        return phoneRepository
                .findAll(getPageRequest(oneBasedIndex, sort))
                .map(phoneConverter::convert);
    }

    private PageRequest getPageRequest(Integer oneBasedIndex, String sort) {
        Sort sorting = sortConvertor.convert(sort);
        Long count = phoneRepository.count();
        return PageRequest.of(oneBasedIndex - 1,
                Math.toIntExact(count) / paginationConfiguration.getPlpMaxPages(), sorting);
    }

    public Optional<PhoneDto> getPhoneById(Long id) {
        return phoneRepository.findOneById(id)
                .map(phoneConverter::convert);
    }

    public Page<PhoneDto> getPhonesSearchPage(Integer oneBasedIndex, String query, Double priceFrom, Double priceTo,
            String sort) {
        Page<Phone> result;
        if (query == null) {
            result = phoneRepository.findAllByPriceBetween(getPriceTo(priceFrom), getPriceFrom(priceTo),
                    getPageRequest(oneBasedIndex, sort));
        } else {
            result = phoneRepository.findAllByBrandContainingAndModelContainingAndPriceBetween(query,
                    getPriceFrom(priceFrom), getPriceTo(priceTo),
                    getPageRequest(oneBasedIndex, sort));
        }
        return result.map(phoneConverter::convert);
    }

    private BigDecimal getPriceFrom(Double priceFrom) {
        return priceFrom == null ? BigDecimal.ZERO : BigDecimal.valueOf(priceFrom);
    }

    private BigDecimal getPriceTo(Double priceTo) {
        return priceTo == null ? BigDecimal.valueOf(Double.MAX_VALUE) : BigDecimal.valueOf(priceTo);
    }

}
