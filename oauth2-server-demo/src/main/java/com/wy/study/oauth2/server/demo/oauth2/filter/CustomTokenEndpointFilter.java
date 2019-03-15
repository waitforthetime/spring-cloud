package com.wy.study.oauth2.server.demo.oauth2.filter;

import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author 王元
 * @description 简单描述一下
 * @date 2019/3/14 下午5:35
 */
public class CustomTokenEndpointFilter extends GenericFilterBean {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        logger.info("custom token endpoint filter");
        Map<String, String[]> parameterMap = request.getParameterMap();
//        parameterMap.put("")

        chain.doFilter(request, response);
    }
}
