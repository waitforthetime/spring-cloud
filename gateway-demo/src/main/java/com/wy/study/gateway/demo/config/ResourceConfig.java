//package com.wy.study.gateway.demo.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//
///**
// * @author 王元
// * @description 简单描述一下
// * @date 2019/3/7 下午2:16
// */
//@Configuration
//@EnableResourceServer
//public class ResourceConfig extends ResourceServerConfigurerAdapter {
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        resources.resourceId("oauth2server");
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .mvcMatchers("/test/world").permitAll()
//                .mvcMatchers("/actuator/**").permitAll()
//                .anyRequest().authenticated();
//    }
//}
