package com.wy.study.zuul.demo.zuul.route;

import com.wy.study.zuul.demo.dao.mapper.ApplicationRouteMapper;
import com.wy.study.zuul.demo.domain.po.ApplicationRoutePO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * @author 王元
 * @description 简单描述一下
 * @date 2019/3/8 下午5:31
 */
public class CustomRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {


    public final static Logger logger = LoggerFactory.getLogger(CustomRouteLocator.class);

    @Resource
    private ApplicationRouteMapper routeMapper;

    private ZuulProperties properties;

    public CustomRouteLocator(String servletPath, ZuulProperties properties) {
        super(servletPath, properties);
        this.properties = properties;
        logger.info("servletPath:{}", servletPath);
    }

    //父类已经提供了这个方法，这里写出来只是为了说明这一个方法很重要！！！
//    @Override
//    protected void doRefresh() {
//        super.doRefresh();
//    }


    @Override
    public void refresh() {
        doRefresh();
    }

    @Override
    protected Map<String, ZuulProperties.ZuulRoute> locateRoutes() {
        LinkedHashMap<String, ZuulProperties.ZuulRoute> routesMap = new LinkedHashMap<String, ZuulProperties.ZuulRoute>();
        //从application.properties中加载路由信息
        routesMap.putAll(super.locateRoutes());
        //从db中加载路由信息
        routesMap.putAll(locateRoutesFromDB());
        //优化一下配置
        LinkedHashMap<String, ZuulProperties.ZuulRoute> values = new LinkedHashMap<>();
        for (Map.Entry<String, ZuulProperties.ZuulRoute> entry : routesMap.entrySet()) {
            String path = entry.getKey();
            // Prepend with slash if not already present.
            if (!path.startsWith("/")) {
                path = "/" + path;
            }
            if (StringUtils.isNotBlank(this.properties.getPrefix())) {
                path = this.properties.getPrefix() + path;
                if (!path.startsWith("/")) {
                    path = "/" + path;
                }
            }
            values.put(path, entry.getValue());
        }
        return values;
    }

    private Map<String, ZuulProperties.ZuulRoute> locateRoutesFromDB() {
        Map<String, ZuulProperties.ZuulRoute> routes = new LinkedHashMap<>();
        List<ApplicationRoutePO> applicationRoutePOS = routeMapper.selectAll();
        List<ZuulProperties.ZuulRoute> zuulRoutes = ApplicationRoutePO.convert2ZuulRoute(applicationRoutePOS);
        for (ZuulProperties.ZuulRoute result : zuulRoutes) {
            if (StringUtils.isBlank(result.getPath()) || StringUtils.isAllEmpty(result.getServiceId(), result.getUrl())) {
                continue;
            }
            routes.put(result.getPath(), result);
        }
        return routes;
    }

}
