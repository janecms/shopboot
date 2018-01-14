package com.hellojd.shopex.service.impl;

import com.hellojd.shopex.plugin.StoragePlugin;
import com.hellojd.shopex.service.PluginService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
@Service
public class PluginServiceImpl implements PluginService {
    @Resource
    private List<StoragePlugin> storagePlugins = new ArrayList();
    @Resource
    private Map<String, StoragePlugin> storagePluginMap = new HashMap();
    @Override
    public List<StoragePlugin> getStoragePlugins() {
        Collections.sort(this.storagePlugins);
        return this.storagePlugins;
    }

    @Override
    public List<StoragePlugin> getStoragePlugins(boolean isEnabled) {
        List enabledList = new ArrayList();
        CollectionUtils.select(this.storagePlugins, new Predicate() {
            @Override
            public boolean evaluate(Object object) {
                StoragePlugin storagePlugin = (StoragePlugin)object;
                return storagePlugin.getIsEnabled() ==isEnabled;
            }
        }, enabledList);
        Collections.sort(enabledList);
        return enabledList;
    }

    @Override
    public StoragePlugin getStoragePlugin(String id) {
        return this.storagePluginMap.get(id);
    }
}
