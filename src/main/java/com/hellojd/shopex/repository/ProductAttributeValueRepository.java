package com.hellojd.shopex.repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hellojd.shopex.bean.ProductAttributeValueBean;
import com.hellojd.shopex.bean.ProductParameterValueBean;
import com.hellojd.shopex.entity.ProductAttribteValue;
import com.hellojd.shopex.entity.ProductParameterValue;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Administrator
 */
@Repository
public interface ProductAttributeValueRepository extends BaseMapper<ProductAttribteValue> {

    public List<ProductAttributeValueBean> getProductAttributeValues(Long productId);
}
