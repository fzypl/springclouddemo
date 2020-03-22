package com.ljj.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author ljj
 * @create 2020-03-13 21:28
 */
@RestController
public class OrderConsulController {
    @Value("${server.port}")
    private static  final  String URL="http://consul-provider-payment ";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/consul")
    public String payemntInfo(){
        String result = restTemplate.getForObject(URL + "/payemnt/consul", String.class);
        return result;
    }
}
