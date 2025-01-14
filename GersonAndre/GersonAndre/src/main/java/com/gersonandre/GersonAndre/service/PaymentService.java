package com.gersonandre.GersonAndre.service;

import com.gersonandre.GersonAndre.model.Payment;
import com.gersonandre.GersonAndre.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public class PaymentService extends BaseService<Payment, Long>{
    @Autowired
    private PaymentRepository repo;
    @Override
    protected PaymentRepository getRepository() {
        return repo;
    }
}
