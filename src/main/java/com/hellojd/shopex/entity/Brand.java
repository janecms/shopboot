package com.hellojd.shopex.entity;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName("d_brand")
public class Brand extends OrderAbleEntity{
    private String name;// 名称
    private String logo;// Logo
    private String url;// 网址
    private String introduction;// 介绍

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Brand)) return false;

        Brand brand = (Brand) o;
        if (this.getId() != null ? !this.getId().equals(brand.getId()) : brand.getId() != null) return false;
        if (name != null ? !name.equals(brand.name) : brand.name != null) return false;
        if (logo != null ? !logo.equals(brand.logo) : brand.logo != null) return false;
        if (url != null ? !url.equals(brand.url) : brand.url != null) return false;
        if (introduction != null ? !introduction.equals(brand.introduction) : brand.introduction != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (this.getId() != null ? getId().hashCode() : 0);
        result = 31 * result + (logo != null ? logo.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (introduction != null ? introduction.hashCode() : 0);
        return result;
    }
}
