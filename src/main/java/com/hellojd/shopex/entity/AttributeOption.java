package com.hellojd.shopex.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@TableName("d_attribute_option")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttributeOption implements Serializable {
    Long attribute;
    String options;
}
