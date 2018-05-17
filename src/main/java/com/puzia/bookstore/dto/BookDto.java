package com.puzia.bookstore.dto;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class BookDto {

    int id;
    String authorFirstName;
    String authorLastName;
    String name;
    BigDecimal price;
    int quantity;

}
