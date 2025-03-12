package com.example.desafio_picpay.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "payment-authorization", url = "https://util.devi.tools/api/v2/authorize")
public interface PaymentAuthorizationClient {
    @GetMapping
    PaymentAuthorizationResponse authorizePayment();
}
