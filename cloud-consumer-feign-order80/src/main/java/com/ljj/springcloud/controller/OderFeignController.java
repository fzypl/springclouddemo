package com.ljj.springcloud.controller;

import com.ljj.springcloud.entities.CommonResult;
import com.ljj.springcloud.entities.Payment;
import com.ljj.springcloud.service.PaymentFeignSerivce;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ljj
 * @create 2020-03-16 15:38
 */
@RestController
public class OderFeignController {
    @Resource
    private PaymentFeignSerivce paymentFeignSerivce;

    @GetMapping("/consumer/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return  paymentFeignSerivce.getPaymentById(id);
    }

}
