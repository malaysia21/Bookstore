package com.puzia.bookstore.service.model;

import lombok.Value;

import java.util.List;

@Value
public class NewOrderDetails {

    int shippingOptionId;
    List<BookOrderDetails> orderedBooks;

}
