package com.hellojd.shopex.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
@TableName("product_category")
public class ProductCategory extends  BaseEntity implements Serializable {
    private static final long serialVersionUID = 3992469837058393712L;
    private String name;// 分类名称
    private String metaKeywords;// 页面关键词
    private String metaDescription;// 页面描述
    private Integer orderList;// 排序
    private String path;// 树路径
    private ProductCategory parent;// 上级分类
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMetaKeywords() {
        return metaKeywords;
    }

    public void setMetaKeywords(String metaKeywords) {
        this.metaKeywords = metaKeywords;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public Integer getOrderList() {
        return orderList;
    }

    public void setOrderList(Integer orderList) {
        this.orderList = orderList;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ProductCategory getParent() {
        return parent;
    }

    public void setParent(ProductCategory parent) {
        this.parent = parent;
    }
}
