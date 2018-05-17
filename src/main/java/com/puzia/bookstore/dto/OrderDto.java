package com.puzia.bookstore.dto;

import lombok.Value;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Value
public class OrderDto {

    int id;
    String userFirstName;
    String userLastName;
    List<BookDto> books;
    BigDecimal shippingCost;
    BigDecimal totalCost;

}
