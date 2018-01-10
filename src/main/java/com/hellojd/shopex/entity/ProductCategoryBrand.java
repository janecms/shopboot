package com.hellojd.shopex.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Administrator
 */
@Data
@TableName("product_category_brand")
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryBrand implements Serializable{
    Long productCategoryId;
    Long brandId;
}
