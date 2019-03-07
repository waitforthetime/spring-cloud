package com.wy.study.gateway.demo;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GatewayDemoApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(GatewayDemoApplication.class).web(WebApplicationType.REACTIVE).run(args);
//        SpringApplication.run(GatewayDemoApplication.class, args);
    }

}
