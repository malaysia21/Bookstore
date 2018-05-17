package com.puzia.bookstore.db.repository;

import com.puzia.bookstore.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByEmail(String email);

}
