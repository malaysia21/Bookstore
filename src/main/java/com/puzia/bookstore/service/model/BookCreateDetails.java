package com.puzia.bookstore.service.model;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class BookCreateDetails {

    int authorId;
    String name;
    BigDecimal price;
    int quantity;

}
