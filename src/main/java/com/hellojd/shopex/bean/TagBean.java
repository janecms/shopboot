package com.hellojd.shopex.bean;

import com.hellojd.shopex.entity.Product;
import com.hellojd.shopex.entity.Tag;

import java.util.HashSet;
import java.util.Set;

public class TagBean extends Tag {

    private Set<Product> products = new HashSet();
}
