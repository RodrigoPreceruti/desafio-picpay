package com.example.desafio_picpay.controller;

import com.example.desafio_picpay.entity.user.CreateUserDTO;
import com.example.desafio_picpay.entity.user.User;
import com.example.desafio_picpay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.findAllUsers());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserDTO requestBody) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.createUser(requestBody));
    }
}
