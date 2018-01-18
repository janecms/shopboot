package com.hellojd.shopex.bean;

import com.hellojd.shopex.entity.Product;
import com.hellojd.shopex.entity.Specification;
import com.hellojd.shopex.entity.SpecificationValue;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Data
public class SpecificationBean extends Specification {
    Set<Product> products = new HashSet<>();
    List<SpecificationValue> specificationValues =new ArrayList();
}
