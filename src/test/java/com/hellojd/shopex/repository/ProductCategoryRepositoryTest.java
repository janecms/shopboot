package com.hellojd.shopex.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hellojd.shopex.entity.ProductCategory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
  @Autowired
  ProductCategoryRepository productCategoryRepository;
  @Test
  public void getRootProductCategoryList() throws Exception {
    List<ProductCategory> root = productCategoryRepository.getRootProductCategoryList();
    assertNotNull(root);
  }

  @Test
  public void getParentProductCategoryList() throws Exception {
  }

  @Test
  public void getChildrenProductCategoryList() throws Exception {
  }

}