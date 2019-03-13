package com.wy.study.zuul.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王元
 * @description 简单描述一下
 * @date 2019/3/8 下午5:06
 */
@RestController
public class TestController {

    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Value("${name}")
    private String name;

    @RequestMapping(path = "test")
    public String hello() {

        logger.info("=================");

        logger.info("name:{}", name);

        logger.info("=================");

        return "success";
    }
}
