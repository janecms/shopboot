package com.hellojd.shopex.plugin;

import com.hellojd.shopex.bean.FileInfo;
import com.hellojd.shopex.entity.PluginConfig;
import com.hellojd.shopex.service.PluginConfigService;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

public abstract class StoragePlugin implements Comparable<StoragePlugin> {
    private PluginConfigService pluginConfigService;
    public final String getId()
    {
        return ((Component)getClass().getAnnotation(Component.class)).value();
    }

    public abstract String getName();

    public abstract String getVersion();

    public abstract String getAuthor();

    public abstract String getSiteUrl();

    public abstract String getInstallUrl();

    public abstract String getUninstallUrl();

    public abstract String getSettingUrl();
    public boolean getIsInstalled()
    {
        return this.pluginConfigService.pluginIdExists(getId());
    }

    public PluginConfig getPluginConfig()
    {
        return this.pluginConfigService.findByPluginId(getId());
    }

    public boolean getIsEnabled()
    {
        PluginConfig localPluginConfig = getPluginConfig();
        return localPluginConfig != null ? localPluginConfig.getIsEnabled().booleanValue() : false;
    }
    public String getAttribute(String name)
    {
        PluginConfig localPluginConfig = getPluginConfig();
        return localPluginConfig != null ? localPluginConfig.getAttribute(name) : null;
    }

    public Integer getOrder()
    {
        PluginConfig localPluginConfig = getPluginConfig();
        return localPluginConfig != null ? localPluginConfig.getOrder() : null;
    }

    public abstract void upload(String path, File file, String contentType);

    public abstract String getUrl(String path);

    public abstract List<FileInfo> browser(String path);

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        StoragePlugin localStoragePlugin = (StoragePlugin)obj;
        return new EqualsBuilder().append(getId(), localStoragePlugin.getId()).isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(17, 37).append(getId()).toHashCode();
    }

    @Override
    public int compareTo(StoragePlugin storagePlugin)
    {
        return new CompareToBuilder().append(getOrder(), storagePlugin.getOrder()).append(getId(), storagePlugin.getId()).toComparison();
    }
}
