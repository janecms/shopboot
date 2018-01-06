package com.hellojd.shopex.service.impl;

import com.hellojd.shopex.entity.ProductCategory;
import com.hellojd.shopex.repository.ProductCategoryRepository;
import com.hellojd.shopex.service.ProductCategoryService;
import com.hellojd.shopex.util.TreeGridUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProductCatalogServiceImpl  implements ProductCategoryService
{
    @Autowired
    ProductCategoryRepository productCategoryRepository;
    @Override
    public Set<ProductCategory> getRootProductCategoryList() {
        Set<ProductCategory> rootList = this.productCategoryRepository.getRootProductCategoryList();
        return TreeGridUtils.build(rootList);
    }

    @Override
    public ProductCategory get(Long id) {
        return productCategoryRepository.getProductById(id);
    }
}
