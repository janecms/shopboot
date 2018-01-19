package com.hellojd.shopex.service;

import com.hellojd.shopex.bean.BrandBean;
import com.hellojd.shopex.entity.Brand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BrandServiceTest {
    @Autowired
    BrandService brandService;
    @Test
    public void test1(){
        brandService.save(new BrandBean());
    }
}