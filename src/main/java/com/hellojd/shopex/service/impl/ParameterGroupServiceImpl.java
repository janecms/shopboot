package com.hellojd.shopex.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.hellojd.shopex.bean.ParameterBean;
import com.hellojd.shopex.bean.ParameterGroupBean;
import com.hellojd.shopex.bean.SpecificationBean;
import com.hellojd.shopex.entity.Parameter;
import com.hellojd.shopex.entity.SpecificationValue;
import com.hellojd.shopex.repository.ParameterRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hellojd.shopex.entity.ParameterGroup;
import com.hellojd.shopex.repository.ParameterGroupRepository;
import com.hellojd.shopex.service.ParameterGroupService;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhaoguoyu
 * @date 2018/1/19
 */
@Slf4j
@Service
public class ParameterGroupServiceImpl extends ShopBaseServiceImpl<ParameterGroupRepository, ParameterGroup> implements
        ParameterGroupService {
    @Autowired
    ParameterRepository parameterRepository;
    @Override
    public void save(ParameterGroupBean parameterGroup) {
        this.baseMapper.insert(parameterGroup);
        final List<ParameterBean> parameters = parameterGroup.getParameters();
        final Long id = parameterGroup.getId();
        if (CollectionUtils.isNotEmpty(parameters)) {
            for (ParameterBean parameter : parameters) {
                parameter.setParameterGroupId(id);
                parameter.setParameterGroup(parameterGroup);
                this.parameterRepository.insert(parameter);
            }
        } else {
            log.warn("没有为规格设置子项列表 {}", id);
        }
    }

    @Override
    public ParameterGroupBean getParameterGroup(Long parameterId) {
        return this.baseMapper.getParameterGroup(parameterId);
    }

    @Override
    public void update(ParameterGroupBean parameterGroup) {
        final ParameterGroupBean po = this.baseMapper.getParameterGroup(parameterGroup.getId());
        this.baseMapper.updateById(parameterGroup);
        final List<ParameterBean> requests = parameterGroup.getParameters();
        List<ParameterBean> persists = po.getParameters();
        this.doUpdateRefResult(requests,persists,new RefUpdater<ParameterBean>(){
            @Override
            public void deleteById(Serializable id) {
                parameterRepository.deleteById(id);
            }
            @Override
            public void updateById(ParameterBean entity) {
                parameterRepository.updateById(entity);
            }
            @Override
            public void insert(ParameterBean entity) {
                entity.setParameterGroupId(parameterGroup.getId());
                parameterRepository.insert(entity);
            }
        });
    }

    @Override
    public Page<ParameterGroupBean> selectBeanPage(Page<ParameterGroupBean> page,ParameterGroup parameterGroup) {
        final List<ParameterGroupBean> records = baseMapper.selectPage(page, parameterGroup);
        page.setRecords(records);
        return page;
    }
}
