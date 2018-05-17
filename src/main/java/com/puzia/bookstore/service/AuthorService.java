package com.puzia.bookstore.service;

import com.puzia.bookstore.db.entity.Author;
import com.puzia.bookstore.db.repository.AuthorJpaRepository;
import com.puzia.bookstore.service.model.NewAuthorDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final AuthorJpaRepository authorJpaRepository;

    @Autowired
    public AuthorService(AuthorJpaRepository authorJpaRepository) {
        this.authorJpaRepository = authorJpaRepository;
    }

    public int addAuthor(NewAuthorDetails newAuthorDetails) {
        Author newAuthor = new Author(
                null,
                newAuthorDetails.getFistName(),
                newAuthorDetails.getLastName()
        );
        return authorJpaRepository.saveAndFlush(newAuthor).getId();
    }

}
