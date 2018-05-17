package com.puzia.bookstore.db.repository;

import com.puzia.bookstore.db.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorJpaRepository extends JpaRepository<Author, Integer> {

}
