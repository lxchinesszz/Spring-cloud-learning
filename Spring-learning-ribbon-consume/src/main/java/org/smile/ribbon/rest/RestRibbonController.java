package org.smile.ribbon.rest;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smile.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Package: org.smile.ribbon.rest
 * @Description:
 * @author: liuxin
 * @date: 2017/12/21 下午5:52
 */
@RestController
public class RestRibbonController {
    public static final Logger logger= LoggerFactory.getLogger(RestRibbonController.class);


    /**
     * 已经实现负载均衡
     */
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping(value = "/user-info")
    public List<ServiceInstance> showInfo() {
        //获取实例信息
        return discoveryClient.getInstances("eureka-server");
    }

    //指定熔断方法
    @HystrixCommand(fallbackMethod = "hystrixDemo")
    @GetMapping(value = "/ribbon/user")
    public User getUser(){
        return restTemplate.getForEntity("http://eureka-provider-service/user",User.class).getBody();
    }

    //执行熔断错误
    public User hystrixDemo(){
        logger.info("熔断方法执行");
        return new User("系统宕机",-1,"1.0.1");
    }
}
