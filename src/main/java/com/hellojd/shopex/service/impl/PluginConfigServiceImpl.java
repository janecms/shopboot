package com.hellojd.shopex.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hellojd.shopex.entity.PluginConfig;
import com.hellojd.shopex.repository.PluginConfigRepository;
import com.hellojd.shopex.service.PluginConfigService;
import org.springframework.stereotype.Service;

/**
 * @author zgy
 */
@Service
public class PluginConfigServiceImpl extends ServiceImpl<PluginConfigRepository,PluginConfig>  implements PluginConfigService {

    @Override
    public boolean pluginIdExists(String id) {
        return false;
    }

    @Override
    public PluginConfig findByPluginId(String id) {
        return null;
    }
}
