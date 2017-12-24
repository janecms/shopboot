package com.hellojd.shopex.repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hellojd.shopex.entity.ProductCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductCategoryRepository extends BaseMapper<ProductCategory> {
    /**
     * 获取所有顶级商品分类集合;
     *
     * @return 所有顶级商品分类集合
     *
     */
    public List<ProductCategory> getRootProductCategoryList();

    /**
     * 根据ProductCategory对象获取所有父类集合，若无父类则返回null;
     *
     * @return 父类集合
     *
     */
    public List<ProductCategory> getParentProductCategoryList(ProductCategory productCategory);

    /**
     * 根据ProductCategory对象获取所有子类集合，若无子类则返回null;
     *
     * @return 子类集合
     *
     */
    public List<ProductCategory> getChildrenProductCategoryList(ProductCategory productCategory);
}
