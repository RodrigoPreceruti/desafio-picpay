package com.example.desafio_picpay.dto;

import java.math.BigDecimal;

public record PaymentRequestDTO(
        BigDecimal value,
        Long payer,
        Long payee
) {
}
