package com.hellojd.shopex.service;

import com.baomidou.mybatisplus.service.IService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hellojd.shopex.entity.Product;
import com.hellojd.shopex.entity.ProductCategory;

import java.util.List;
import java.util.Set;

public interface ProductCategoryService{

    /**
	 * 获取所有顶级商品分类集合;
	 * 
	 * @return 所有顶级商品分类集合
	 * 
	 */
	public Set<ProductCategory> getRootProductCategoryList();
	public ProductCategory get(Long id);

}