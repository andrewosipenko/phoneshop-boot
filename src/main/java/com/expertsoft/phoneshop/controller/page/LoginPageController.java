package com.expertsoft.phoneshop.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.expertsoft.phoneshop.PhoneShopConstants.LOGIN_PATH;

@Controller
@RequestMapping(LOGIN_PATH)
public class LoginPageController {

    private static final String LOGIN_PAGE = "loginPage";


    @GetMapping
    public String getLoginPage() {
        return LOGIN_PAGE;
    }

}
