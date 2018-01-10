package com.hellojd.shopex.service.impl;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hellojd.shopex.bean.ProductCategoryBean;
import com.hellojd.shopex.entity.ProductCategoryBrand;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCatalogServiceImplTest {
  @Autowired
  ProductCatalogServiceImpl productCatalogServiceImpl;
  @Test
  public void sysnProductCategoryBrands() throws Exception {
    ProductCategoryBean cat = productCatalogServiceImpl.getProductCategoryById(10L);
    productCatalogServiceImpl.sysnProductCategoryBrands(10L,null,cat.getBrands());
    cat = productCatalogServiceImpl.getProductCategoryById(10L);
    Set<ProductCategoryBrand> reqs = new HashSet<>();
    reqs.add(new ProductCategoryBrand(10L,1L));
    reqs.add(new ProductCategoryBrand(10L,2L));
    productCatalogServiceImpl.sysnProductCategoryBrands(10L,reqs,cat.getBrands());
     cat = productCatalogServiceImpl.getProductCategoryById(10L);
     assertEquals(2,cat.getBrands().size());

    reqs.add(new ProductCategoryBrand(10L,3L));
    productCatalogServiceImpl.sysnProductCategoryBrands(10L,reqs,cat.getBrands());
    cat = productCatalogServiceImpl.getProductCategoryById(10L);
    assertEquals(3,cat.getBrands().size());
    Set<ProductCategoryBrand> reqs2 = new HashSet<>();
    reqs2.add(new ProductCategoryBrand(10L,1L));
    reqs2.add(new ProductCategoryBrand(10L,3L));
    productCatalogServiceImpl.sysnProductCategoryBrands(10L,reqs2,cat.getBrands());
    cat = productCatalogServiceImpl.getProductCategoryById(10L);
    assertEquals(2,cat.getBrands().size());
  }
@Test
  public void eqs(){
    final ProductCategoryBrand pc1 = new ProductCategoryBrand(10L, 1L);
    final ProductCategoryBrand pc2 = new ProductCategoryBrand(10L, 1L);
    assertTrue(pc1.equals(pc2));
  }
}