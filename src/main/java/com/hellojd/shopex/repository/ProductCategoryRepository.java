package com.hellojd.shopex.repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hellojd.shopex.bean.ProductCategoryBean;
import com.hellojd.shopex.entity.ProductCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @author Administrator
 */
@Repository
public interface ProductCategoryRepository extends BaseMapper<ProductCategory>{
    /**
     * 获取所有顶级商品分类集合;
     *
     * @return 所有顶级商品分类集合
     *
     */
    public Set<ProductCategoryBean> getRootProductCategoryList();
    public ProductCategoryBean getProductCategoryById(Long id);
    /**
     * 根据ProductCategory对象获取所有子类集合，若无子类则返回null;
     *
     * @return 子类集合
     *
     */
    public List<ProductCategoryBean> getChildrenProductCategoryList(ProductCategory productCategory);

}
