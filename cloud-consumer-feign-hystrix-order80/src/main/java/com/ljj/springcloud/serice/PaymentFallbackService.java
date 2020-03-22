package com.ljj.springcloud.serice;

import org.springframework.stereotype.Component;

/**
 * @author ljj
 * @create 2020-03-17 12:06
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentinfo_ok(Integer id) {
        return "----PaymentFallbackService fall back paymentinfo_ok";
    }

    @Override
    public String paymentinfo_timeout(Integer id) {
        return "----PaymentFallbackService fall back paymentinfo_timeout";
    }
}
