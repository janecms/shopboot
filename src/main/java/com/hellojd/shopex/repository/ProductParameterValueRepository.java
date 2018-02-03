package com.hellojd.shopex.repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hellojd.shopex.bean.ProductParameterValueBean;
import com.hellojd.shopex.entity.ProductParameterValue;
import com.hellojd.shopex.entity.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Administrator
 */
@Repository
public interface ProductParameterValueRepository extends BaseMapper<ProductParameterValue> {

    public List<ProductParameterValueBean> getProductParameterValues(Long productId);
}
