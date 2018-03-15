package org.smile.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Package: org.smile.zuul
 * @Description: zuul 做网关
 * @author: liuxin
 * @date: 2017/12/22 上午10:24
 */
@EnableZuulProxy
@SpringCloudApplication
public class ZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class,args);
    }
}
