package com.example.desafio_picpay.service;

import com.example.desafio_picpay.entity.user.CreateUserDTO;
import com.example.desafio_picpay.entity.user.User;
import com.example.desafio_picpay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<User> findAllUsers() {
        return this.repository.findAll();
    }

    public User createUser(CreateUserDTO requestBody) {
        User user = new User(requestBody);
        this.repository.save(user);

        return user;
    }
}
