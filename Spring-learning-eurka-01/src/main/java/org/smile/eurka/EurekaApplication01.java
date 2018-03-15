package org.smile.eurka;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Package: PACKAGE_NAME
 * @Description: 集群注册
 * @author: liuxin
 * @date: 2017/12/21 下午2:01
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication01 {
    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaApplication01.class).web(true).bannerMode(Banner.Mode.LOG).run(args);
    }
}
