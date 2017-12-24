package com.hellojd.shopex.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hellojd.shopex.entity.Product;
import com.hellojd.shopex.entity.ProductCategory;
import com.hellojd.shopex.repository.ProductCategoryRepository;
import com.hellojd.shopex.service.ProductCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductCatalogServiceImpl extends ServiceImpl<ProductCategoryRepository,ProductCategory> implements ProductCategoryService
{
    @Override
    public List<ProductCategory> getRootProductCategoryList() {
        return null;
    }

    @Override
    public List<ProductCategory> getParentProductCategoryList(ProductCategory productCategory) {
        return null;
    }

    @Override
    public List<ProductCategory> getParentProductCategoryList(Product product) {
        return null;
    }

    @Override
    public List<ProductCategory> getProductCategoryPathList(ProductCategory productCategory) {
        return null;
    }

    @Override
    public List<ProductCategory> getProductCategoryPathList(Product product) {
        return null;
    }

    @Override
    public List<ProductCategory> getChildrenProductCategoryList(ProductCategory productCategory) {
        return null;
    }

    @Override
    public List<ProductCategory> getChildrenProductCategoryList(Product product) {
        return null;
    }

    @Override
    public List<ProductCategory> getProductCategoryTreeList() {
        return null;
    }
}
