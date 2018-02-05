package com.hellojd.shopex.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

/**
 * 商品属性
 * @author Administrator
 */
@TableName("product_attribute_value")
@Data
public class ProductAttribteValue {
    Long productId;
    Long attributeValueKey;
    String attributeValue;
}
