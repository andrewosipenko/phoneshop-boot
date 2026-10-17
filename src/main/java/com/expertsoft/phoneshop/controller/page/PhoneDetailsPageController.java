package com.expertsoft.phoneshop.controller.page;

import com.expertsoft.phoneshop.data.PhoneData;
import com.expertsoft.phoneshop.facade.PhoneFacade;
import com.expertsoft.phoneshop.persistence.model.Phone;
import com.expertsoft.phoneshop.service.PhoneService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

import java.util.Optional;

import static com.expertsoft.phoneshop.PhoneShopConstants.PHONES_PATH;

@Controller
@RequestMapping(PHONES_PATH)
public class PhoneDetailsPageController {
    private static final String PHONE = "phone";
    private static final String PHONE_DETAILS_PAGE = "phoneDetailsPage";

    @Resource
    private PhoneService phoneService;

    @Resource
    private PhoneFacade phoneFacade;

    @GetMapping( "/{phoneId}")
    public String getPhoneDetails(@PathVariable("phoneId") Long phoneId, Model model) {
        PhoneData phone = phoneFacade.getPhone(phoneId);

        model.addAttribute(PHONE, phone);
        return PHONE_DETAILS_PAGE;
    }
}
