package com.expertsoft.phoneshop.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "phoneshop")
public class PlpConfiguration {

    private int plpMaxPages;
    private int plpMaxProducts;

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
