package com.wy.study.configdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王元
 * @description 简单描述一下
 * @date 2019/3/6 上午11:28
 */
@RestController
@RequestMapping(path = "test")
public class HelloWordController {
    private static Logger logger = LoggerFactory.getLogger(HelloWordController.class);

//    @Value("${name}")
//    private String name;

    @RequestMapping(path = "hello")
    public String hello() {
        logger.info("=================");
//        logger.info("name:{}", name);
        logger.info("hello");
        logger.info("=================");
        return "success";
    }
}
