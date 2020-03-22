package com.ljj.springcloud.service.impl;

import com.ljj.springcloud.dao.PaymentDao;
import com.ljj.springcloud.entities.Payment;
import com.ljj.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ljj
 * @create 2020-03-12 12:12
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
