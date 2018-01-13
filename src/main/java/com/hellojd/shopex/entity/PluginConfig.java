package com.hellojd.shopex.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import java.beans.Transient;
import java.util.HashMap;
import java.util.Map;
@TableName("plugin_config")
public class PluginConfig extends OrderAbleEntity {
    String pluginId;
    Boolean isEnabled;
    private Map<String, String> attributes = new HashMap();
    public Map<String, String> getAttributes()
    {
        return this.attributes;
    }
    public String getPluginId()
    {
        return this.pluginId;
    }

    public void setPluginId(String pluginId)
    {
        this.pluginId = pluginId;
    }
    public Boolean getIsEnabled()
    {
        return this.isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled)
    {
        this.isEnabled = isEnabled;
    }

    @Transient
    public String getAttribute(String name)
    {
        if ((getAttributes() != null) && (name != null)) {
            return (String) getAttributes().get(name);
        }
        return null;
    }

    @Transient
    public void setAttribute(String name, String value)
    {
        if ((getAttributes() != null) && (name != null)) {
            getAttributes().put(name, value);
        }
    }
}
