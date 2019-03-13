package com.wy.study.zuul.demo.domain.po;

import lombok.Data;

/**
 * @author 王元
 * @description 简单描述一下
 * @date 2019/3/11 下午5:51
 */
@Data
public class InterfaceRolePO {

    /**
     * 自增长id
     */
    private Long id;
    /**
     * 路由模版id
     */
    private Long applicationRouteId;
    /**
     * 接口路径
     */
    private String interfacePath;
    /**
     * 权限ID
     */
    private Long authorityId;
    /**
     * 描述
     */
    private String desc;
    /**
     * 1有效，0无效
     */
    private int recordStatus;
    /**
     * 创建时间
     */
    private String addTime;
    /**
     * 更新时间
     */
    private String modifyTime;
}
