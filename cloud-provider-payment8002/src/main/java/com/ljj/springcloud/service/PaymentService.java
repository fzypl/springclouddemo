package com.ljj.springcloud.service;

import com.ljj.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author ljj
 * @create 2020-03-12 12:12
 */
public interface PaymentService {
    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
