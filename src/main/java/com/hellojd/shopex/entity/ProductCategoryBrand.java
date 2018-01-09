package com.hellojd.shopex.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Administrator
 */
@Data
@TableName("product_category_brand")
public class ProductCategoryBrand implements Serializable{
    Long productCategoryId;
    Long brandId;
}
