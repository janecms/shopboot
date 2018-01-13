package com.hellojd.shopex.service;

import com.baomidou.mybatisplus.service.IService;
import com.hellojd.shopex.entity.PluginConfig;

public interface PluginConfigService extends IService<PluginConfig> {
    public abstract boolean pluginIdExists(String pluginId);

    public abstract PluginConfig findByPluginId(String pluginId);
}
