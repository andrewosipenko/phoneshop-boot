package com.expertsoft.phoneshop.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.expertsoft.phoneshop.service.PhoneService;

import static com.expertsoft.phoneshop.PhoneShopConstants.PHONES_PATH;

import javax.annotation.Resource;

@Controller
@RequestMapping(PHONES_PATH)
public class PhoneDetailsPageController {

    private static final String PHONE_DETAILS_PAGE = "phoneDetailsPage";
    private static final String PHONE_ATTR = "phone";

    @Resource
    private PhoneService phoneService;

    @GetMapping("/{phoneId}")
    public String getPhoneDetails(@PathVariable("phoneId") Long phoneId, Model model) {
        phoneService.getPhoneById(phoneId)
                .ifPresent(phone -> model.addAttribute(PHONE_ATTR, phone));
        return PHONE_DETAILS_PAGE;
    }
}
