package com.hellojd.shopex.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.hellojd.shopex.bean.ParameterBean;
import com.hellojd.shopex.bean.ParameterGroupBean;
import com.hellojd.shopex.entity.ParameterGroup;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 *
 * @author zhaoguoyu
 * @date 2018/1/19
 */
public interface ParameterGroupService extends IService<ParameterGroup>{
    void save(ParameterGroupBean parameterGroup);

    ParameterGroupBean getParameterGroup(Long parameterId);

    void update(ParameterGroupBean parameterGroup);

    Page<ParameterGroupBean> selectBeanPage(Page<ParameterGroupBean> page,ParameterGroup parameterGroup);
}
