package com.hellojd.shopex.service;

import com.hellojd.shopex.plugin.StoragePlugin;

import java.util.List;

/**
 * @author Administrator
 */
public interface PluginService {

    public abstract List<StoragePlugin> getStoragePlugins();

    public abstract List<StoragePlugin> getStoragePlugins(boolean isEnabled);


    public abstract StoragePlugin getStoragePlugin(String id);
}
