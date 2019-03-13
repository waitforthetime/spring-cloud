package com.wy.study.zuul.demo.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.wy.study.zuul.demo.constants.ZuulConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 王元
 * @description 简单描述一下
 * @date 2019/3/13 下午2:20
 */
@Component
public class ProfileFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(ProfileFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1000;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        Map<String, List<String>> params = currentContext.getRequestQueryParams();
        logger.info("params:{}", params);
        currentContext.set(ZuulConstants.RIBBON_METADATA_PROFILE, "gray");
        return null;
    }
}
