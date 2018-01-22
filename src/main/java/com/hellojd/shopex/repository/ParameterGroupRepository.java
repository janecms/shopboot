package com.hellojd.shopex.repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hellojd.shopex.bean.ParameterBean;
import com.hellojd.shopex.bean.ParameterGroupBean;
import com.hellojd.shopex.entity.ParameterGroup;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author zhaoguoyu
 * @date 2018/1/19
 */
@Repository
public interface ParameterGroupRepository  extends BaseMapper<ParameterGroup>{
    ParameterGroupBean getParameterGroup(Long id);
    List<ParameterBean> getParameters(Long parameterGroupId);
    List<ParameterGroupBean> selectPage(RowBounds rowBounds,ParameterGroup parameterGroup);
}
