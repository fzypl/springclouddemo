package com.ljj.springcloud.controller;

import com.ljj.springcloud.entities.CommonResult;
import com.ljj.springcloud.entities.Payment;
import com.ljj.springcloud.service.PaymentService;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ljj
 * @create 2020-03-12 12:19
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;
    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        //System.out.println("");
        log.info("*******查询结果:"+result);
        if(result>0){
            return new CommonResult(200,"插入数据成功,serverPort:"+serverPort,result);
        }else {
            return new CommonResult(444,"插入数据失败",null);
        }
    }
    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment result = paymentService.getPaymentById(id);
        log.info("*******查询结果:"+result);
        if(result!=null){
            return new CommonResult(200,"查询数据成功,serverPort:"+serverPort,result);
        }else {
            return new CommonResult(444,"没有对应的实体",null);
        }
    }
    @GetMapping("/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String str:services){
            log.info("******elemet:"+str);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance:instances){
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+
                    instance.getPort()+"\t"+instance.getUri());
        }
        return  this.discoveryClient;
    }
    @GetMapping("/payment/lb")
    public String getServerPort(){
        return serverPort;
    }
}
