package com.expertsoft.phoneshop.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "phoneshop")
public class PagesConfiguration {

    private int plpMaxPages = 5;
    private int plpMaxProducts = 5;
    private int maxUsers = 5;

    public int getMaxUsers() {
        return maxUsers;
    }

    public void setMaxUsers(int maxUsers) {
        this.maxUsers = maxUsers;
    }

    public int getPlpMaxPages() {
        return plpMaxPages;
    }

    public void setPlpMaxPages(int plpMaxPages) {
        this.plpMaxPages = plpMaxPages;
    }

    public int getPlpMaxProducts() {
        return plpMaxProducts;
    }

    public void setPlpMaxProducts(int plpMaxProducts) {
        this.plpMaxProducts = plpMaxProducts;
    }
}
