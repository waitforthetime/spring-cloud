package com.wy.study.zuul.demo.service.impl;

import com.wy.study.zuul.demo.service.RefreshRouteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * @author 王元
 * @description 简单描述一下
 * @date 2019/3/8 下午6:13
 */
@Service
public class RefreshRouteServiceImpl implements RefreshRouteService {


    @Autowired
    ApplicationEventPublisher publisher;

    @Autowired
    RouteLocator routeLocator;

    public void refreshRoute() {
        RoutesRefreshedEvent routesRefreshedEvent = new RoutesRefreshedEvent(routeLocator);
        publisher.publishEvent(routesRefreshedEvent);
    }
}
