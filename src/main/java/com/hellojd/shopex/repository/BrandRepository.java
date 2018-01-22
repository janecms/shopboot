package com.hellojd.shopex.repository;

import java.util.List;
import java.util.Set;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hellojd.shopex.entity.Brand;
import com.hellojd.shopex.entity.ProductCategoryBrand;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 */
@Repository
public interface BrandRepository extends BaseMapper<Brand> {
  public void saveProductCategoryBrand(ProductCategoryBrand pcb);
  public void deleteProductCategoryBrand(ProductCategoryBrand pcb);
  public Set<Brand> getBrands(Long productCategoryId);
}
