package com.puzia.bookstore.db.repository;

import com.puzia.bookstore.db.entity.Author;
import com.puzia.bookstore.db.entity.Book;
import com.puzia.bookstore.db.entity.Order;
import com.puzia.bookstore.db.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OrderJpaRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    OrderJpaRepository repository;

    @Test
    public void dupa() {
        Author author = entityManager.persistAndFlush(new Author(null, "Author", "Fancy"));
        User user = entityManager.persistAndFlush(new User(null, "Domini", "Racer", "elo"));

        Book book1 = entityManager.persistAndFlush(new Book(null, author, "Ksionszka1", BigDecimal.ONE, 10));
        Book book2 = entityManager.persistAndFlush(new Book(null, author, "Ksionszka2", BigDecimal.ZERO, 11));

        entityManager.persistAndFlush(new Order(null, user, new ArrayList<>(Arrays.asList(book1, book2)), BigDecimal.TEN, BigDecimal.TEN));

        System.out.println(repository.findAll());
    }
}