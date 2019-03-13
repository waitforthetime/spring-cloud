package com.wy.study.zuul.demo.config;

import com.wy.study.zuul.demo.zuul.route.CustomRouteLocator;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author 王元
 * @description 简单描述一下
 * @date 2019/3/11 上午10:56
 */
@Configuration
public class ZuulConfig {

    @Resource
    private ZuulProperties zuulProperties;

    @Resource
    protected ServerProperties server;

    @Bean
    public CustomRouteLocator customRouteLocator() {
        return new CustomRouteLocator(server.getServlet().getPath(), zuulProperties);
    }
}
