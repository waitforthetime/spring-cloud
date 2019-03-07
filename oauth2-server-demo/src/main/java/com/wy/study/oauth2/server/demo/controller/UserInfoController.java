package com.wy.study.oauth2.server.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author 王元
 * @description 简单描述一下
 * @date 2019/3/7 下午2:10
 */
@RestController
public class UserInfoController {

    private Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @RequestMapping(path = "user")
    public Principal user(Principal principal) {

        logger.info("================");
        logger.info("principal info:{}", principal);
        logger.info("================");
        return principal;
    }
}
