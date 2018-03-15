package org.smile.consul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package: org.smile.consul
 * @Description: 工具类
 * @author: liuxin
 * @date: 2017/12/21 下午4:38
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ConsulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsulApplication.class,args);
    }
}
