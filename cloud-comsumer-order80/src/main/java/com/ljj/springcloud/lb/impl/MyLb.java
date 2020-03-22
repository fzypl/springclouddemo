package com.ljj.springcloud.lb.impl;

import com.ljj.springcloud.lb.LoadBalance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ljj
 * @create 2020-03-15 21:32
 */
@Component
public class MyLb implements LoadBalance {

    private AtomicInteger atomicInteger=new AtomicInteger(0);

    public final int getandIncratment(){
        int current=0;
        int next=0;
        do {
            current=this.atomicInteger.get();
            next=(current>=Integer.MAX_VALUE?0:current+1);
        }while (!this.atomicInteger.compareAndSet(current,next));
        System.out.println("这是第几次访问"+next);
        return next;
    }
    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = getandIncratment() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
