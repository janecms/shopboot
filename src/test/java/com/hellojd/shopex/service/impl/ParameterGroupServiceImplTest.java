package com.hellojd.shopex.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.hellojd.shopex.bean.ParameterGroupBean;
import com.hellojd.shopex.entity.ParameterGroup;
import com.hellojd.shopex.service.ParameterGroupService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@ContextConfiguration(initializers = ConfigFileApplicationContextInitializer.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class ParameterGroupServiceImplTest {
    @Autowired
    ParameterGroupService parameterGroupService;
    @Test
    public void getSelectPage() throws Exception {
        final Page<ParameterGroupBean> page = new Page<>();
        page.setSize(5);
        final Page<ParameterGroupBean> parameterGroupPage = parameterGroupService.selectBeanPage(page,null);
        assertNotNull(parameterGroupPage);
    }

}