package com.wy.study.zuul.demo.config;

import com.wy.study.zuul.demo.zuul.role.CustomFilterSecurityInterceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import javax.annotation.Resource;

/**
 * @author 王元
 * @description 简单描述一下
 * @date 2019/3/11 下午2:51
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("zuul-server");
    }

    @Resource
    private CustomFilterSecurityInterceptor securityInterceptor;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .mvcMatchers("/actuator/**", "/zuul-backend/**", "/test", "/oauth2-server/**").permitAll()
//                .antMatchers("").hasRole("ROLE_USER")
                .anyRequest().authenticated();
        http.addFilterBefore(securityInterceptor, FilterSecurityInterceptor.class);
    }
}
