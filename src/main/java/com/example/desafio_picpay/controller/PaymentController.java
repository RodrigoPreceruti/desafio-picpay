package com.example.desafio_picpay.controller;

import com.example.desafio_picpay.dto.PaymentRequestDTO;
import com.example.desafio_picpay.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
public class PaymentController {
    @Autowired
    private PaymentService service;

    @PostMapping
    public ResponseEntity<?> makePayment(@RequestBody PaymentRequestDTO requestBody) {
        this.service.makePayment(requestBody);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
