package com.ljj.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ljj
 * @create 2020-03-13 21:12
 */
@RestController
public class PaymentController {
    @Value("${server.port}")
    private  String serverPort;

    @RequestMapping("/payment/consul")
    public String paymentConsul(){
        return "springcloud with consul "+serverPort;
    }
}
