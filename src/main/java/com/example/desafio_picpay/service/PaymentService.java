package com.example.desafio_picpay.service;

import com.example.desafio_picpay.client.PaymentAuthorizationClient;
import com.example.desafio_picpay.dto.PaymentRequestDTO;
import com.example.desafio_picpay.entity.User;
import com.example.desafio_picpay.entity.UserType;
import com.example.desafio_picpay.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PaymentAuthorizationClient paymentAuthorizationClient;

    public void makePayment(PaymentRequestDTO requestBody) {
        User payer = this.repository
                .findById(requestBody.payer())
                .orElseThrow(EntityNotFoundException::new);

        User payee = this.repository
                .findById(requestBody.payee())
                .orElseThrow(EntityNotFoundException::new);

        if (!this.validatePayment(payer)) {
            throw new RuntimeException("Lojista não pode efetuar uma transferência");
        }

        if (payer.getBalance().compareTo(requestBody.value()) < 0) {
            throw new RuntimeException("Saldo insuficiente para realizar a trasnferência!");
        }

        if (!this.validateAuthorizationPayment()) {
            throw new RuntimeException("Não foi possível concluir a transferência!");
        }

        payer.setBalance(payer.getBalance().subtract(requestBody.value()));
        payee.setBalance(payee.getBalance().add(requestBody.value()));

        this.repository.save(payer);
        this.repository.save(payee);
    }

    public Boolean validatePayment(User user) {
        if (user.getUserType().equals(UserType.SHOPKEEPER)) {
            return false;
        }

        return true;
    }

    public Boolean validateAuthorizationPayment() {
        return this.paymentAuthorizationClient
                .authorizePayment()
                .data()
                .authorization();
    }
}
