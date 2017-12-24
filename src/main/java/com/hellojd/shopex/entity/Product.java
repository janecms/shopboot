package com.hellojd.shopex.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
@TableName("product")
@Data
public class Product implements Serializable{
    private static final long serialVersionUID = -7492639752670189553L;
    // 重量单位（克、千克、吨）
    public enum WeightUnit {
        g, kg, t
    }

    public static final int MAX_BEST_PRODUCT_LIST_COUNT = 20;// 精品商品列表最大商品数
    public static final int MAX_NEW_PRODUCT_LIST_COUNT = 20;// 新品商品列表最大商品数
    public static final int MAX_HOT_PRODUCT_LIST_COUNT = 20;// 热销商品列表最大商品数
    public static final int DEFAULT_PRODUCT_LIST_PAGE_SIZE = 12;// 商品列表默认每页显示数

    private String productSn;// 货号
    private String name;// 商品名称
    private BigDecimal price;// 商品价格
    private BigDecimal marketPrice;// 市场价格
    private Double weight;// 商品重量
    private WeightUnit weightUnit;// 重量单位
    private Integer store;// 商品库存数量
    private Integer freezeStore;// 被占用库存数
    private Integer point;// 积分
    private Boolean isMarketable;// 是否上架
    private Boolean isBest;// 是否为精品商品
    private Boolean isNew;// 是否为新品商品
    private Boolean isHot;// 是否为热销商品
    private String description;// 描述
    private String metaKeywords;// 页面关键词
    private String metaDescription;// 页面描述
    private String htmlFilePath;// HTML静态文件路径
    private String productImageListStore;// 商品图片路径存储

    private ProductCategory productCategory;// 商品分类
    private Brand brand;// 品牌
    private Map<Attribute, String> productAttributeMap;// 商品属性存储
    private Map<Parameter, String>  parameterValue;
    Map<MemberRank, BigDecimal> memberPrice;

    private Set<Tag> tags = new HashSet();

    private Set<Specification> specifications = new HashSet();
    private Set<SpecificationValue> specificationValues = new HashSet();
}
