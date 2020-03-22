package com.ljj.springcloud.controller;

import com.ljj.springcloud.service.PayementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ljj
 * @create 2020-03-16 20:30
 */
@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PayementService payementService;
    @Value("${server.port}")
    private  String serverPort;
    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentinfo_ok(@PathVariable("id") Integer id){
        String result=payementService.paymentInfo_OK(id);
        log.info("*****result"+result);
        return result;
    }
    
    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentinfo_timeout(@PathVariable("id") Integer id){
        String result=payementService.paymentInfo_TIMEOUT(id);
        log.info("*****result"+result);
        return result;
    }
    //服务熔断
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
            String result=payementService.paymentCircuitBreaker(id);
            return result;
    }
}
