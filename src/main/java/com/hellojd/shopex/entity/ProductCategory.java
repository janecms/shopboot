package com.hellojd.shopex.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@TableName("product_category")
@Data
public class ProductCategory extends  BaseEntity implements Serializable {
    private static final long serialVersionUID = 3992469837058393712L;
    private String name;// 分类名称
    private String seoKeywords;// 页面关键词
    private String seoTitle;// 页面关键词
    private String seoDescription;// 页面描述
    private Integer order;// 排序
    private String treePath;// 树路径
    private ProductCategory parent;// 上级分类
    private List<ProductCategory> children;
    Integer grade;
}
