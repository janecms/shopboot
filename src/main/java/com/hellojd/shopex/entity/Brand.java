package com.hellojd.shopex.entity;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName("d_brand")
public class Brand extends BaseEntity{
    private String name;// 名称
    private String logo;// Logo
    private String url;// 网址
    private String introduction;// 介绍
    private Integer orders;// 排序

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }
}
