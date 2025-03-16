package com.example.desafio_picpay.service;

import com.example.desafio_picpay.client.PaymentAuthorizationClient;
import com.example.desafio_picpay.dto.PaymentRequestDTO;
import com.example.desafio_picpay.entity.payment.Payment;
import com.example.desafio_picpay.entity.user.User;
import com.example.desafio_picpay.entity.user.UserType;
import com.example.desafio_picpay.repository.PaymentRepository;
import com.example.desafio_picpay.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentAuthorizationClient paymentAuthorizationClient;

    public List<Payment> findAllPayments() {
        return this.repository.findAll();
    }

    public void makePayment(PaymentRequestDTO requestBody) {
        User payer = this.userRepository
                .findById(requestBody.payer())
                .orElseThrow(EntityNotFoundException::new);

        User payee = this.userRepository
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

        Payment payment = new Payment();
        payment.setPayer(payer);
        payment.setPayee(payee);
        payment.setValue(requestBody.value());

        this.repository.save(payment);
        this.userRepository.save(payer);
        this.userRepository.save(payee);
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
