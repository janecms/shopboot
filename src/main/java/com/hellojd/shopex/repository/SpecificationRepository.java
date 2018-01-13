package com.hellojd.shopex.repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hellojd.shopex.bean.SpecificationBean;
import com.hellojd.shopex.entity.Specification;
import com.hellojd.shopex.entity.SpecificationValue;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SpecificationRepository extends BaseMapper<Specification>{
    SpecificationBean getSpecification(Long specificationId);
    Set<SpecificationValue> getSpecificationValues(Long specificationId);
}
