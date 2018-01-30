package com.hellojd.shopex.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
@TableName("product")
@Data
public class Product extends  BaseEntity implements Serializable{
    @Pattern(regexp="^[0-9a-zA-Z_-]+$")
    @Length(max=200)
    private String sn;
    @Length(max=200)
    private String name;
    private String fullName;

    private BigDecimal price;
    private BigDecimal cost;
    private BigDecimal marketPrice;
    private String image;
    private String unit;
    private Integer weight;
    private Integer stock;
    private Integer allocatedStock;
    @Length(max=200)
    private String stockMemo;
    @Min(0L)
    private Long point;
    @NotNull
    private Boolean isMarketable;
    @NotNull
    private Boolean isList;
    @NotNull
    private Boolean isTop;
    @NotNull
    private Boolean isGift;
    private String introduction;
    @Length(max=200)
    private String memo;
    @Length(max=200)
    private String keyword;
    private String seoTitle;
    @Length(max=200)
    private String seoKeywords;
    @Length(max=200)
    private String seoDescription;

    private Float score;
    private Long totalScore;
    private Long scoreCount;
    private Long hits;
    private Long weekHits;
    private Long monthHits;
    private Long sales;
    private Long weekSales;
    private Long monthSales;
    private Date weekHitsDate;
    private Date monthHitsDate;
    private Date weekSalesDate;
    private Date monthSalesDate;

    public void setKeyword(String keyword)
    {
        if (keyword != null) {
            keyword = keyword.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "");
        }
        this.keyword = keyword;
    }
    public void setSeoKeywords(String seoKeywords)
    {
        if (seoKeywords != null) {
            seoKeywords = seoKeywords.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "");
        }
        this.seoKeywords = seoKeywords;
    }

}
