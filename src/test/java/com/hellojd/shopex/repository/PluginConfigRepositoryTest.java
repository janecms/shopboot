package com.hellojd.shopex.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.*;

import com.hellojd.shopex.entity.PluginConfig;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PluginConfigRepositoryTest {
    @Autowired
    PluginConfigRepository pluginConfigRepository;
}