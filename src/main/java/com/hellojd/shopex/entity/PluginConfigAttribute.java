package com.hellojd.shopex.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("plugin_config_attribute")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PluginConfigAttribute {
    String attributeKey;
    String attributeValue;
}
