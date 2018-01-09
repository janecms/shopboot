package com.hellojd.shopex.service;

import com.baomidou.mybatisplus.service.IService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hellojd.shopex.bean.ProductCategoryBean;
import com.hellojd.shopex.bean.treeview.TreeViewBean;
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
	public Set<ProductCategoryBean> getRootProductCategoryList();
	public ProductCategoryBean getProductCategoryById(Long id);
	public List<TreeViewBean> buildCategoryTree(ProductCategoryBean selectNode);
	public int update(ProductCategoryBean categoryBean,List<Long> brandIds);
}