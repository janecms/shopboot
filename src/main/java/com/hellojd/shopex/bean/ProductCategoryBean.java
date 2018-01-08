package com.hellojd.shopex.bean;

import java.util.Set;

import com.hellojd.shopex.entity.Attribute;
import com.hellojd.shopex.entity.Brand;
import com.hellojd.shopex.entity.ParameterGroup;
import com.hellojd.shopex.entity.Product;
import com.hellojd.shopex.entity.ProductCategory;

import lombok.Data;

/**
 * Created by zhaoguoyu on 2018/1/8.
 */
@Data
public class ProductCategoryBean extends ProductCategory implements  CategoryBean<ProductCategoryBean>{
  // 上级分类
  ProductCategoryBean parent;
  Set<ProductCategoryBean> children;
  Set<Product> products;
  Set<Brand> brands;
  Set<ParameterGroup> parameterGroups;
  Set<Attribute> attributes;
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    ProductCategoryBean that = (ProductCategoryBean) o;
    if (this.getId() != null ? !this.getId().equals(that.getId()) : that.getId() != null) return false;
    if (name != null ? !name.equals(that.name) : that.name != null) return false;
    if (seoTitle != null ? !seoTitle.equals(that.seoTitle) : that.seoTitle != null) return false;
    if (seoKeywords != null ? !seoKeywords.equals(that.seoKeywords) : that.seoKeywords != null) return false;
    if (seoDescription != null ? !seoDescription.equals(that.seoDescription) : that.seoDescription != null)
      return false;
    if (treePath != null ? !treePath.equals(that.treePath) : that.treePath != null) return false;
    return grade != null ? grade.equals(that.grade) : that.grade == null;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (this.getId() != null ? this.getId().hashCode() : 0);
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (seoTitle != null ? seoTitle.hashCode() : 0);
    result = 31 * result + (seoKeywords != null ? seoKeywords.hashCode() : 0);
    result = 31 * result + (seoDescription != null ? seoDescription.hashCode() : 0);
    result = 31 * result + (treePath != null ? treePath.hashCode() : 0);
    result = 31 * result + (grade != null ? grade.hashCode() : 0);
    return result;
  }
}
