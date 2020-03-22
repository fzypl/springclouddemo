package com.ljj.springcloud.controller;

import com.ljj.springcloud.serice.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ljj
 * @create 2020-03-16 21:02
 */
@RestController
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderController {
    @Resource
    PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    String paymentinfo_ok(@PathVariable("id") Integer id){
       return  paymentHystrixService.paymentinfo_ok(id);
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    /*@HystrixCommand(fallbackMethod = "paymentTimeOutFallback",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
    })*/
    @HystrixCommand
    String paymentinfo_timeout(@PathVariable("id") Integer id){
        return  paymentHystrixService.paymentinfo_timeout(id);
    }
    public String paymentTimeOutFallback(@PathVariable("id") Integer id){
        return "我是80消费者的，对方的消费系统繁忙";
    }
    //全局fallback
    public String payment_Global_FallbackMethod(){
        return "global 异常处理信息";
    }
}
