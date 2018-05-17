package com.puzia.bookstore.domain.shipping;

import com.puzia.bookstore.exception.InvalidShippingMethodId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShippingOptionFactory {

    private final List<ShippingOption> shippingOptionList;

    @Autowired
    public ShippingOptionFactory(List<ShippingOption> shippingOptionList) {
        this.shippingOptionList = shippingOptionList;
    }

    public ShippingOption getShippingOptionById(int id) {
        return shippingOptionList.stream()
                .filter(shippingOption -> id == shippingOption.getId())
                .findFirst()
                .orElseThrow(InvalidShippingMethodId::new);
    }

}
