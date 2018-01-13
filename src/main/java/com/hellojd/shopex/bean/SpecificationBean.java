package com.hellojd.shopex.bean;

import com.hellojd.shopex.entity.Product;
import com.hellojd.shopex.entity.Specification;
import com.hellojd.shopex.entity.SpecificationValue;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
@Data
public class SpecificationBean extends Specification {
    Set<Product> products = new HashSet<>();
    Set<SpecificationValue> specificationValues =new HashSet();
}
