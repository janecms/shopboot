package com.hellojd.shopex.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hellojd.shopex.bean.*;
import com.hellojd.shopex.entity.*;
import com.hellojd.shopex.repository.ProductAttributeValueRepository;
import com.hellojd.shopex.repository.ProductParameterValueRepository;
import com.hellojd.shopex.repository.ProductRepository;
import com.hellojd.shopex.repository.SpecificationRepository;
import com.hellojd.shopex.service.BrandService;
import com.hellojd.shopex.service.ProductCategoryService;
import com.hellojd.shopex.service.ProductService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Administrator
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductRepository,Product> implements ProductService{
    @Autowired
    ProductParameterValueRepository productParameterValueRepository;
    @Autowired
    ProductAttributeValueRepository productAttributeValueRepository;
    @Autowired
    SpecificationRepository specificationRepository;

    @Override
    public boolean snExists(String sn) {
        Product product = new Product();
        product.setSn(sn);
        Wrapper<Product> wrapper = new EntityWrapper<>(product);
        return this.baseMapper.selectCount(wrapper)>0;
    }

    @Override
    public Page<ProductBean> selectPage(Page<ProductBean> page,ProductBean probe) {
        final List<ProductBean> records = this.baseMapper.selectPage(page,probe);
        if (CollectionUtils.isNotEmpty(records)){
            records.forEach(productBean -> {
                productBean.setProductCategory(this.productCategoryService.getProductCategoryById(productBean.getProductCategoryId()));
            });
        }
        page.setRecords(records);
        return page;
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
    public ProductBean getProduct(Long productId) {
        final ProductBean product = this.baseMapper.getProduct(productId);
        final Long brandId = product.getBrandId();
        if(brandId!=null){
            product.setBrand(brandService.selectById(brandId));
        }
        final Long productCategoryId = product.getProductCategoryId();
        if(productCategoryId!=null){
            final ProductCategoryBean category = productCategoryService.getProductCategoryById(productCategoryId);
            product.setProductCategory(category);
            final Map<ParameterBean, String> parameterValueMap = getParameterValueMap(productId);
            product.setParameterValue(parameterValueMap);
            final Map<AttributeBean, String> attributeValueMap = this.getAttributeValueMap(productId);
            product.setAttributeValueMap(attributeValueMap);
        }
        final Set<Specification> specifications = specificationRepository.getSpecificationsByProductId(productId);
        product.setSpecifications(specifications);
        final Set<SpecificationValue> specificationValues = specificationRepository.getSpecificationValuesByProductId(productId);
        product.setSpecificationValues(specificationValues);
        return product;
    }



    /**
     *
     * @param productId
     * @return
     */
    public Map<ParameterBean, String> getParameterValueMap(Long productId){
        ProductParameterValue probe = new ProductParameterValue();
        probe.setProductId(productId);
        final List<ProductParameterValueBean> productParameterValues = this.productParameterValueRepository.getProductParameterValues(productId);
        Map<ParameterBean, String> resultParameterMap = new HashMap<>();
        if(CollectionUtils.isNotEmpty(productParameterValues)){
            productParameterValues.forEach(productParameterValue -> {
                resultParameterMap.put(productParameterValue.getParameter(),productParameterValue.getParameterValue());
            });
        }
        return resultParameterMap;
    }

    public Map<AttributeBean, String> getAttributeValueMap(Long productId){
        ProductParameterValue probe = new ProductParameterValue();
        probe.setProductId(productId);
        final List<ProductAttributeValueBean> productParameterValues = this.productAttributeValueRepository.getProductAttributeValues(productId);
        Map<AttributeBean, String> resultAttributeMap = new HashMap<>();
        if(CollectionUtils.isNotEmpty(productParameterValues)){
            productParameterValues.forEach(productParameterValue -> {
                resultAttributeMap.put(productParameterValue.getAttribute(),productParameterValue.getAttributeValue());
            });
        }
        return resultAttributeMap;
    }
}
