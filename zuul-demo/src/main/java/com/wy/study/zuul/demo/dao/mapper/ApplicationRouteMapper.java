package com.wy.study.zuul.demo.dao.mapper;

import com.wy.study.zuul.demo.domain.po.ApplicationRoutePO;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 王元
 * @description 简单描述一下
 * @date 2019/3/8 下午5:52
 */
@Mapper
public interface ApplicationRouteMapper {

    List<ApplicationRoutePO> selectAll();

}
