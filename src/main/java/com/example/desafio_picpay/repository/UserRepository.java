package com.example.desafio_picpay.repository;

import com.example.desafio_picpay.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
