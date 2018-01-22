package com.hellojd.shopex.repository;

import com.hellojd.shopex.bean.ProductCategoryBean;
import com.hellojd.shopex.entity.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    ProductCategoryRepository productCategoryRepository;
    @Test
    public void getRootProductCategorySet() throws Exception {
        Set<ProductCategoryBean> rootSet = productCategoryRepository.getRootProductCategorySet();
        assertNotNull(rootSet);

        ProductCategory category = productCategoryRepository.selectById(29);
        assertNotNull(category);
    }

}