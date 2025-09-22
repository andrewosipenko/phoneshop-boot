package com.expertsoft.phoneshop.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.expertsoft.phoneshop.service.UserService;

import static com.expertsoft.phoneshop.PhoneShopConstants.ADMIN_PATH;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping(ADMIN_PATH)
public class AdminPageController {
    private static final String ADMIN_PANEL_PAGE = "admin/adminPanelPage";
    private static final String USERS = "users";
    @Resource
    private UserService userService;

    @GetMapping
    public String showAllUsers(Model model) {
        model.addAttribute(USERS, userService.getAllUsers());
        return ADMIN_PANEL_PAGE;
    }

}
