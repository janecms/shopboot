package com.hellojd.shopex.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hellojd.shopex.bean.Pager;
import com.hellojd.shopex.entity.Product;
import com.hellojd.shopex.entity.ProductCategory;
import com.hellojd.shopex.repository.ProductRepository;
import com.hellojd.shopex.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class ProductServiceImpl extends ServiceImpl<ProductRepository,Product> implements ProductService{
    @Override
    public List<Product> getProductList(ProductCategory productCategory) {
        return null;
    }

    @Override
    public List<Product> getProductList(int firstResult, int maxResults) {
        return null;
    }

    @Override
    public List<Product> getProductList(ProductCategory productCategory, int firstResult, int maxResults) {
        return null;
    }

    @Override
    public List<Product> getProductList(Date beginDate, Date endDate, int firstResult, int maxResults) {
        return null;
    }

    @Override
    public Pager getProductPager(ProductCategory productCategory, Pager pager) {
        return null;
    }

    @Override
    public List<Product> getBestProductList(int maxResults) {
        return null;
    }

    @Override
    public List<Product> getBestProductList(ProductCategory productCategory, int maxResults) {
        return null;
    }

    @Override
    public List<Product> getHotProductList(int maxResults) {
        return null;
    }

    @Override
    public List<Product> getHotProductList(ProductCategory productCategory, int maxResults) {
        return null;
    }

    @Override
    public List<Product> getNewProductList(int maxResults) {
        return null;
    }

    @Override
    public List<Product> getNewProductList(ProductCategory productCategory, int maxResults) {
        return null;
    }

    @Override
    public Pager search(Pager pager) {
        return null;
    }

    @Override
    public Long getStoreAlertCount() {
        return null;
    }

    @Override
    public Long getMarketableProductCount() {
        return null;
    }

    @Override
    public Long getUnMarketableProductCount() {
        return null;
    }
}
