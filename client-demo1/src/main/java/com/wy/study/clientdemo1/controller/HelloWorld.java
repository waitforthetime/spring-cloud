package com.wy.study.clientdemo1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王元
 * @description 简单描述一下
 * @date 2019/3/5 下午6:05
 */
@RestController
@RequestMapping(path = "test")
public class HelloWorld {
    private static Logger logger = LoggerFactory.getLogger(HelloWorld.class);

    @Value("${name}")
    private String name;

    @RequestMapping(path = "hello")
    public String hello() {

        logger.info("================");

        logger.info("hello world!, name:{}", name);

        logger.info("================");
        return "success";
    }

}
