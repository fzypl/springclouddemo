package com.ljj.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;


import java.util.List;

/**
 * @author ljj
 * @create 2020-03-15 21:29
 */

public interface LoadBalance {
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
