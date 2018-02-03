package com.hellojd.shopex.repository;

import com.hellojd.shopex.bean.ProductParameterValueBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductParameterValueRepositoryTest {
    @Autowired
    ProductParameterValueRepository productParameterValueRepository;
    @Test
    public void getProductParameterValues() throws Exception {
        final List<ProductParameterValueBean> productParameterValues = productParameterValueRepository.getProductParameterValues(27L);
        assertNotNull(productParameterValues);
    }

}