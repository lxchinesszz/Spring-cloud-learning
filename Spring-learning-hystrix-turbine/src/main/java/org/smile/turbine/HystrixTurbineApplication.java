package org.smile.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * http://blog.csdn.net/forezp/article/details/70233227
 * @Package: org.smile.turbine
 * @Description: 聚合熔断
 * 将在注册中心已经注册的服务的熔断,聚合在一起显示
 * 监控流
 * http://localhost:12000/turbine.stream
 * @author: liuxin
 * @date: 2017/12/28 下午5:01
 */
@EnableTurbine
@SpringBootApplication
public class HystrixTurbineApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixTurbineApplication.class,args);
    }
}
