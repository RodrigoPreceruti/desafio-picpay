package com.example.desafio_picpay.repository;

import com.example.desafio_picpay.entity.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
