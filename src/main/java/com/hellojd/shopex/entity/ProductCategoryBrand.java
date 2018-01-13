package com.hellojd.shopex.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import com.hellojd.shopex.bean.RefBean;
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
public class ProductCategoryBrand implements RefBean,Serializable{
    Long productCategoryId;
    Long brandId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCategoryBrand that = (ProductCategoryBrand) o;

        if (productCategoryId != null ? !productCategoryId.equals(that.productCategoryId) : that.productCategoryId != null)
            return false;
        return brandId != null ? brandId.equals(that.brandId) : that.brandId == null;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (productCategoryId != null ? productCategoryId.hashCode() : 0);
        result = 31 * result + (brandId != null ? brandId.hashCode() : 0);
        return result;
    }

    @Override
    public Serializable getParentId() {
        return productCategoryId;
    }

    @Override
    public Serializable getChildId() {
        return brandId;
    }
}
