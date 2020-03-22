package com.ljj.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author ljj
 * @create 2020-03-16 20:24
 */
//没有使用接口 无所谓
@Service
public class PayementService {
    /**
     * 正常访问
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id){
        return "线程池："+Thread.currentThread().getName()+"paymentInfo_OK id:"+id;
    }
    @HystrixCommand(fallbackMethod = "paymentinfo_TimeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String paymentInfo_TIMEOUT(Integer id)  {
        int time=5;
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+"paymentInfo_TIMEOUT id:"+id+"耗时"+time;
    }
    public String paymentinfo_TimeoutHandler(Integer id){
        return "线程池："+Thread.currentThread().getName()+"paymentInfo_TIMEOUT id:"+id;
    }
    //服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value ="true" ),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if(id<0){
            throw  new RuntimeException("id 不能为负数");
        }
        String serialNumber= IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功 流水号："+serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id 不能为负数 请稍后在试 id:\t"+id;
    }
}
