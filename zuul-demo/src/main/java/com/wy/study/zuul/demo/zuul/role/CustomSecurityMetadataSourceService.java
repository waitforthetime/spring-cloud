package com.wy.study.zuul.demo.zuul.role;

import com.google.common.collect.Lists;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 王元
 * @description 简单描述一下
 * @date 2019/3/11 下午5:49
 */
@Component
public class CustomSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

//    @Resource
//    private InterfaceRoleMapper interfaceRoleMapper;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation filterInvocation = (FilterInvocation) object;
        HttpServletRequest request = filterInvocation.getRequest();
        String url = "/client/test/hello";
        AntPathRequestMatcher antPathMatcher = new AntPathRequestMatcher(url);
        if (antPathMatcher.matches(request)) {
            return Collections.singletonList(new SecurityConfig("1"));
        }

        return Lists.newArrayList();
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.equals(clazz);
    }
}
