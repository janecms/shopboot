package com.hellojd.shopex.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hellojd.shopex.bean.Pager;
import com.hellojd.shopex.bean.ProductBean;
import com.hellojd.shopex.entity.Product;
import com.hellojd.shopex.entity.ProductCategory;
import com.hellojd.shopex.repository.ProductRepository;
import com.hellojd.shopex.service.BrandService;
import com.hellojd.shopex.service.ProductCategoryService;
import com.hellojd.shopex.service.ProductService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class ProductServiceImpl extends ServiceImpl<ProductRepository,Product> implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Override
    public boolean snExists(String sn) {
        Product product = new Product();
        product.setSn(sn);
        Wrapper<Product> wrapper = new EntityWrapper<>(product);
        return this.baseMapper.selectCount(wrapper)>0;
    }

    @Override
    public Product findBySn(String sn) {
        Product product = new Product();
        product.setSn(sn);
        return this.baseMapper.selectOne(product);
    }

    @Override
    public boolean snUnique(String previousSn, String currentSn) {
        if (StringUtils.equalsIgnoreCase(previousSn, currentSn)) {
            return true;
        }
        return !this.snExists(currentSn);
    }

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
    @Autowired
    BrandService brandService;
    @Autowired
    ProductCategoryService productCategoryService;

    @Override
    public ProductBean getProduct(Long id) {
        final ProductBean product = this.baseMapper.getProduct(id);
        final Long brandId = product.getBrandId();
        if(brandId!=null){
            product.setBrand(brandService.selectById(brandId));
        }
        final Long productCategoryId = product.getProductCategoryId();
        if(productCategoryId!=null){
            product.setProductCategory(productCategoryService.getProductCategoryById(productCategoryId));
        }

        return product;
    }
}
