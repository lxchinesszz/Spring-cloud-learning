package org.smile.eurka.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Package: org.smile.eurka.rest
 * @Description: ${todo}
 * @author: liuxin
 * @date: 2017/12/21 下午11:27
 */
@RestController
public class UserInfoController {
    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping(value = "/user-info")
    public List<ServiceInstance> showInfo() {
        //获取实例信息
        return discoveryClient.getInstances("eureka-server");
    }
}
