package com.puzia.bookstore.exception;

import com.puzia.bookstore.db.entity.Book;
import com.puzia.bookstore.dto.BookDto;

public class UnsufficientBookQuantityException extends RuntimeException {

    public UnsufficientBookQuantityException(Book bookDto) {

    }

}
