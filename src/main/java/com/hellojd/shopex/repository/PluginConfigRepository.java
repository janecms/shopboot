package com.hellojd.shopex.repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hellojd.shopex.entity.Brand;
import com.hellojd.shopex.entity.PluginConfig;
import com.hellojd.shopex.entity.PluginConfigAttribute;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Set;

/**
 * @author Administrator
 */
@Repository
public interface PluginConfigRepository extends BaseMapper<PluginConfig> {

    public Set<PluginConfigAttribute> getConfigAttributes(Long pluginId);
}
