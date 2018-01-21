package com.hellojd.shopex.service;

import com.baomidou.mybatisplus.service.IService;
import com.hellojd.shopex.bean.AttributeBean;
import com.hellojd.shopex.bean.SpecificationBean;
import com.hellojd.shopex.entity.Attribute;
import com.hellojd.shopex.entity.Brand;

/**
 * @author Administrator
 */
public interface AttributeService extends IService<Attribute> {
    AttributeBean getAttribute(Long attributeId);
    void save(AttributeBean attribute);
    void update(AttributeBean attribute);
}
