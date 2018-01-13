package com.hellojd.shopex.bean;

import com.hellojd.shopex.entity.Brand;
import com.hellojd.shopex.entity.Product;
import com.hellojd.shopex.entity.ProductCategory;
import com.hellojd.shopex.entity.Promotion;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
/**
 * @author Administrator
 */
@Data
public class BrandBean extends Brand {
    private Set<Product> products = new HashSet();
    private Set<ProductCategory> productCategories = new HashSet();
    private Set<Promotion> promotions = new HashSet();
}
