package com.example.desafio_picpay.entity.user;

import java.math.BigDecimal;

public record CreateUserDTO(
        String name,
        String document,
        String email,
        String password,
        BigDecimal balance,
        UserType userType
) {
}
