package com.hellojd.shopex.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.hellojd.shopex.bean.CategoryBean;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;
@Data
@TableName("product_category")
public class ProductCategory extends  OrderAbleEntity implements Serializable {
    private static final long serialVersionUID = 3992469837058393712L;
   protected String name;
   protected String seoTitle;
   protected String seoKeywords;
   protected String seoDescription;
   protected String treePath;// 树路径
   protected Integer grade;
   protected Long parentId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProductCategory that = (ProductCategory) o;
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

    @Override
    public String toString() {
        return "ProductCategory{" +
                "id=" + this.getId()+
                ",name='" + name + '\'' +
                ", treePath='" + treePath + '\'' +
                '}';
    }
}
