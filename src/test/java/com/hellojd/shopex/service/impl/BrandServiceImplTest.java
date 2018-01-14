package com.hellojd.shopex.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.hellojd.shopex.entity.Brand;
import com.hellojd.shopex.service.BrandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class BrandServiceImplTest {
    @Autowired
    BrandService brandService;
    @Test
    public void findList() throws Exception {
        final Page<Brand> brandPage = brandService.selectPage(new Page<>());
        assertNotNull(brandPage.getRecords());
    }

}