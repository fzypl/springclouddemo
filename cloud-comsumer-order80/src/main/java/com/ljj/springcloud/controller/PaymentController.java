package com.ljj.springcloud.controller;


import com.ljj.springcloud.lb.LoadBalance;
import com.ljj.springcloud.entities.CommonResult;
import com.ljj.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author ljj
 * @create 2020-03-12 12:19
 */
@RestController
@Slf4j
public class PaymentController {
    //public static  final String PAYMENT_URL="http://localhost:8001";
    public static  final String PAYMENT_URL="http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private LoadBalance loadBalance;
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;
    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return  restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return  restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }
   /* @GetMapping()
    public CommonResult<Payment> getPaymentCommonResult(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> entity =
                restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if(entity.getStatusCode().is2xxSuccessful()){
            return  entity.getBody();
        }else{
            return  new CommonResult<>(444,"操作失败");
        }
    }*/
   @GetMapping("/consumer/payment/lb")
    public  String getPaymentLB(){
       List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
       if(null==instances || instances.size()<=0){
           System.out.println("有问题");
            return null;
       }
       ServiceInstance serviceInstance = loadBalance.instances(instances);
       URI uri = serviceInstance.getUri();
       return  restTemplate.getForObject(uri+"/payment/lb",String.class);
   }
}
