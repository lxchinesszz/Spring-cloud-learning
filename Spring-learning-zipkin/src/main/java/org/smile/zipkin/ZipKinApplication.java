package org.smile.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin.server.EnableZipkinServer;

/**
 * @Package: org.smile.zipkin
 * @Description: 基于http方式发送span traceId

 * @author: liuxin
 * @date: 2017/12/21 下午8:22
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableZipkinServer
public class ZipKinApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZipKinApplication.class,args);
    }
}
