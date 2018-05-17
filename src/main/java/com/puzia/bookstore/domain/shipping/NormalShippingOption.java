package com.puzia.bookstore.domain.shipping;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class NormalShippingOption implements ShippingOption {

    @Override
    public int getId() {
        return 1;
    }

    @Override
    public BigDecimal getPrice() {
        return BigDecimal.ONE;
    }

}
