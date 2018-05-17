package com.puzia.bookstore.service;

import com.puzia.bookstore.db.entity.User;
import com.puzia.bookstore.db.repository.UserJpaRepository;
import com.puzia.bookstore.service.model.NewUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserJpaRepository userJpaRepository;

    @Autowired
    public UserService(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    public int addUser(NewUserDetails newUserDetails) {
        User newUser = new User(
                null,
                newUserDetails.getFistName(),
                newUserDetails.getLastName(),
                newUserDetails.getEmail()
        );
        return userJpaRepository.saveAndFlush(newUser).getId();
    }

}
