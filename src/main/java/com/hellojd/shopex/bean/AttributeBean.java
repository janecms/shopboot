package com.hellojd.shopex.bean;

import com.hellojd.shopex.entity.Attribute;
import com.hellojd.shopex.entity.AttributeOption;
import com.hellojd.shopex.entity.ProductCategory;
import lombok.Data;

import java.util.List;
@Data
public class AttributeBean extends Attribute {
    ProductCategoryBean productCategory;
    List<AttributeOption> options;

}
