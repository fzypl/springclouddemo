package com.ljj.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author ljj
 * @create 2020-03-21 12:40
 */
@SpringBootApplication
@EnableConfigServer
//@EnableEurekaClient
public class MainConfig3344 {
    public static void main(String[] args) {
        SpringApplication.run(MainConfig3344.class,args);
    }
}
