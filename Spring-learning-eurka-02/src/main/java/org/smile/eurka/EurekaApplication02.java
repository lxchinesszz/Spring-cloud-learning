package org.smile.eurka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Package: PACKAGE_NAME
 * @Description: 集群注册
 * @author: liuxin
 * @date: 2017/12/21 下午2:01
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication02 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication02.class,args);
    }
}
