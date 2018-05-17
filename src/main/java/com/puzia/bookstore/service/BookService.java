package com.puzia.bookstore.service;

import com.puzia.bookstore.db.entity.Author;
import com.puzia.bookstore.db.entity.Book;
import com.puzia.bookstore.db.repository.AuthorJpaRepository;
import com.puzia.bookstore.db.repository.BookJpaRepository;
import com.puzia.bookstore.dto.BookDto;
import com.puzia.bookstore.exception.MissingAuthorException;
import com.puzia.bookstore.exception.MissingBookException;
import com.puzia.bookstore.service.model.BookCreateDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final AuthorJpaRepository authorJpaRepository;
    private final BookJpaRepository bookJpaRepository;

    @Autowired
    public BookService(AuthorJpaRepository authorJpaRepository,
                       BookJpaRepository bookJpaRepository) {
        this.authorJpaRepository = authorJpaRepository;
        this.bookJpaRepository = bookJpaRepository;
    }

    public int addBook(BookCreateDetails bookCreateDetails) {
        Author author = authorJpaRepository.findById(bookCreateDetails.getAuthorId())
                .orElseThrow(MissingAuthorException::new);
        Book book = createBook(bookCreateDetails, author);
        return bookJpaRepository.saveAndFlush(book).getId();
    }

    public BookDto getBook(int id) {
        return bookJpaRepository.findDtoById(id)
                .orElseThrow(MissingBookException::new);
    }

    public List<BookDto> getBooks() {
        return bookJpaRepository.findAllDtosBy();
    }

    private Book createBook(BookCreateDetails bookCreateDetails, Author author) {
        return new Book(
                null,
                author,
                bookCreateDetails.getName(),
                bookCreateDetails.getPrice(),
                bookCreateDetails.getQuantity()
        );
    }

}
