package com.expertsoft.phoneshop.converter;

import com.expertsoft.phoneshop.data.PhoneData;
import com.expertsoft.phoneshop.persistence.model.Phone;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PhoneToPhoneDataConverter implements Converter<Phone, PhoneData> {

    @Override
    public PhoneData convert(Phone phone) {
        PhoneData target = new PhoneData();
        target.setId(phone.getId());
        target.setBrand(phone.getBrand());
        target.setDescription(phone.getDescription());
        target.setPrice(phone.getPrice().doubleValue());
        target.setImage(phone.getImage());
        target.setModel(phone.getModel());
        return target;
    }
}
