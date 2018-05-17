package com.puzia.bookstore.db.repository;

import com.puzia.bookstore.db.entity.Book;
import com.puzia.bookstore.dto.BookDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookJpaRepository extends JpaRepository<Book, Integer> {

    Optional<BookDto> findDtoById(int id);

    List<BookDto> findAllDtosBy();

}
