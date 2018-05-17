package com.puzia.bookstore.domain.shipping;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ExpressShippingOption implements ShippingOption {

    @Override
    public int getId() {
        return 2;
    }

    @Override
    public BigDecimal getPrice() {
        return BigDecimal.TEN;
    }

}
