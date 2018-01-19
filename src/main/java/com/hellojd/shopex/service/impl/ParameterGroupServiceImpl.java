package com.hellojd.shopex.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hellojd.shopex.entity.ParameterGroup;
import com.hellojd.shopex.repository.ParameterGroupRepository;
import com.hellojd.shopex.service.ParameterGroupService;

/**
 *
 * @author zhaoguoyu
 * @date 2018/1/19
 */
@Service
public class ParameterGroupServiceImpl extends ServiceImpl<ParameterGroupRepository,ParameterGroup> implements
    ParameterGroupService{
}
