package com.wy.study.oauth2.server.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王元
 * @description 简单描述一下
 * @date 2019/3/7 上午11:01
 */
@RestController
@RequestMapping(path = "test")
public class HelloController {

    private static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping(path = "hello")
    public String hello() {
        logger.info("==================");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication) {
            logger.info("authentication:{}", authentication);
        }
        logger.info("==================");
        return "success";
    }

    @RequestMapping(path = "world")
    public String world() {
        logger.info("==================");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication) {
            logger.info("authentication:{}", authentication);
        } else {
            logger.info("not need authentication");
        }
        logger.info("==================");
        return "success";
    }
}
