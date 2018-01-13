package com.hellojd.shopex.repository;

import com.hellojd.shopex.bean.SpecificationBean;
import com.hellojd.shopex.entity.SpecificationValue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpecificationRepositoryTest {
    @Autowired
    SpecificationRepository specificationRepository;
    @Test
    public void getSpecification() throws Exception {
        final SpecificationBean specification = specificationRepository.getSpecification(2L);
        assertNotNull(specification);
        assertNotNull(specification.getSpecificationValues());
    }

    @Test
    public void getSpecificationValuesList() throws Exception {
        final Set<SpecificationValue> specificationValues = specificationRepository.getSpecificationValues(2L);
        assertNotNull(specificationValues);
    }

}