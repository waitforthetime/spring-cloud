package com.wy.study.zuul.demo.zuul.role;

import com.google.common.collect.Sets;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 王元
 * @description 简单描述一下
 * @date 2019/3/12 上午10:03
 */
@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {


    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if (CollectionUtils.isEmpty(configAttributes)) {
            return;
        }

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Set<String> users = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        //根据角色查询权限
        Set<String> auths = Sets.newHashSet("1", "3");
        Set<String> roles = configAttributes.stream()
                .map(ConfigAttribute::getAttribute)
                .collect(Collectors.toSet());

        if (CollectionUtils.isEmpty(auths) || CollectionUtils.isEmpty(roles)) {
            throw new AccessDeniedException("no right");
        }
        String match = auths.stream()
                .filter(roles::contains)
                .findFirst()
                .orElse(null);
        if (null == match) {
            throw new AccessDeniedException("no right");
        }
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.equals(clazz);
    }
}
