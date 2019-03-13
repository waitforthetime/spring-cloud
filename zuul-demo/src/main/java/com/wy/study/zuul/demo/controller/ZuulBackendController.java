package com.wy.study.zuul.demo.controller;

import com.wy.study.zuul.demo.domain.api.BasicResult;
import com.wy.study.zuul.demo.service.RefreshRouteService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 王元
 * @description 简单描述一下
 * @date 2019/3/8 下午6:24
 */
@RestController
@RequestMapping(path = "zuul-backend")
public class ZuulBackendController {

    private static Logger logger = LoggerFactory.getLogger(ZuulBackendController.class);

    @Resource
    private RefreshRouteService refreshRouteService;

    @RequestMapping(path = "refresh/route")
    public BasicResult refreshRoute() {
        logger.info("refresh route");
        refreshRouteService.refreshRoute();
        return BasicResult.success();
    }

}
