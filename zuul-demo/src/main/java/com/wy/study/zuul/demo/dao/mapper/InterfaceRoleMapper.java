package com.wy.study.zuul.demo.dao.mapper;

import com.wy.study.zuul.demo.domain.po.InterfaceRolePO;

import java.util.List;

/**
 * @author 王元
 * @description 简单描述一下
 * @date 2019/3/11 下午5:51
 */
public interface InterfaceRoleMapper {

    List<InterfaceRolePO> selectAll();
}
