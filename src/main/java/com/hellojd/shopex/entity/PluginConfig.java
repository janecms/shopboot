package com.hellojd.shopex.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.collections.CollectionUtils;

import java.beans.Transient;
import java.util.*;

/**
 * @author Administrator
 */
@TableName("plugin_config")
public class PluginConfig extends OrderAbleEntity {
    String pluginId;
    @TableField("is_enabled")
    Boolean enabled;
    private transient Set<PluginConfigAttribute> attributes = new HashSet();

    private  transient Map<String,String> attributesMap = new HashMap<>();

    public String getPluginId()
    {
        return this.pluginId;
    }

    public void setPluginId(String pluginId)
    {
        this.pluginId = pluginId;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Transient
    public String getAttribute(String name)
    {
        if ((attributesMap != null) && (name != null)) {
            return attributesMap.get(name);
        }
        return null;
    }

    @Transient
    public void setAttribute(String name, String value)
    {
        attributes.add(new PluginConfigAttribute(name,value));
    }

    public void setAttributes(Set<PluginConfigAttribute> attributes) {
        this.attributes = attributes;
        if(CollectionUtils.isNotEmpty(attributes)){
            final Iterator<PluginConfigAttribute> iter = attributes.iterator();
            while (iter.hasNext()){
                final PluginConfigAttribute pluginConfigAttribute = iter.next();
                this.attributesMap.put(pluginConfigAttribute.attributeKey,pluginConfigAttribute.attributeValue);
            }

        }
    }
}
