package com.wy.study.zuul.demo.domain.po;

import com.google.common.collect.Sets;

import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;

/**
 * @author 王元
 * @description 简单描述一下
 * @date 2019/3/8 下午5:37
 */
@Data
public class ApplicationRoutePO {
    /**
     * 自增长主键
     */
    private Long id;

    /**
     * 微服务id
     */
    private String serviceId;

    /**
     * 路由路径
     */
    private String routePath;

    /**
     * 路由目标地址，如果是微服务，仅提供serviceId即可
     */
    private String url;

    /**
     * 记录状态
     */
    private int recordStatus;

    /**
     * 创建时间
     */
    private String addTime;

    /**
     * 修改时间
     */
    private String modifyTime;


    public ZuulProperties.ZuulRoute convert2ZuulRoute() {

        String id = String.valueOf(this.id);
        return new ZuulProperties.ZuulRoute(id, routePath, serviceId, url,
                true, true, Sets.newHashSet());
    }

    public static List<ZuulProperties.ZuulRoute> convert2ZuulRoute(List<ApplicationRoutePO> applicationRoutePOS) {

        if (CollectionUtils.isEmpty(applicationRoutePOS)) {
            return new ArrayList<>();
        }

        return applicationRoutePOS.stream().map(ApplicationRoutePO::convert2ZuulRoute)
                .collect(Collectors.toList());
    }
}
