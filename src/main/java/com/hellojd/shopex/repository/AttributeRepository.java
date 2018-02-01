package com.hellojd.shopex.repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hellojd.shopex.bean.AttributeBean;
import com.hellojd.shopex.bean.ParameterGroupBean;
import com.hellojd.shopex.entity.Attribute;
import com.hellojd.shopex.entity.AttributeOption;
import com.hellojd.shopex.entity.ParameterGroup;
import com.hellojd.shopex.entity.Tag;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author zhaoguoyu
 */
@Repository
public interface AttributeRepository extends BaseMapper<Attribute> {

    AttributeBean getAttribute(Long attributeId);
    Set<AttributeBean> getAttributes(Long categoryId);

    List<AttributeOption> getAttributeOptions(Long attributeId);

    List<AttributeBean> selectPage(RowBounds rowBounds, AttributeBean attribute);
}
