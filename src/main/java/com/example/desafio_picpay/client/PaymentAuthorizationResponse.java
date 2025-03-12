package com.example.desafio_picpay.client;

public record PaymentAuthorizationResponse(
        String status,
        Data data
) {
}
