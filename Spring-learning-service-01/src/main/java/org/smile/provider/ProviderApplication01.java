package org.smile.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Package: org.smile.provider
 * @Description: 服务提供者1
 * @author: liuxin
 * @date: 2017/12/21 下午3:39
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ProviderApplication01 {
    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication01.class,args);
    }
}
