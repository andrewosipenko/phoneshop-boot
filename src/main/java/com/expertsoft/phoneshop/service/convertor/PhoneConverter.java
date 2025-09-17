package com.expertsoft.phoneshop.service.convertor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.expertsoft.phoneshop.persistence.model.Phone;
import com.expertsoft.phoneshop.service.dto.PhoneDto;

@Component
public class PhoneConverter implements Converter<Phone, PhoneDto> {

    @Override
    public PhoneDto convert(@NonNull Phone source) {
        PhoneDto phoneDto = new PhoneDto();
        phoneDto.setBrand(source.getBrand());
        phoneDto.setDescription(source.getDescription());
        phoneDto.setId(source.getId());
        phoneDto.setImage(source.getImage());
        phoneDto.setPrice(source.getPrice().doubleValue());
        phoneDto.setModel(source.getModel());
        return phoneDto;
    }

}
