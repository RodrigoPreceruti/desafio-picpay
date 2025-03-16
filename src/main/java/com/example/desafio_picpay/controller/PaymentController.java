package com.example.desafio_picpay.controller;

import com.example.desafio_picpay.dto.PaymentRequestDTO;
import com.example.desafio_picpay.entity.payment.Payment;
import com.example.desafio_picpay.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<Payment>> findAllPayments() {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.findAllPayments());
    }
}
