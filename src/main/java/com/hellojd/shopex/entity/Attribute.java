package com.hellojd.shopex.entity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.List;

/**
 * @author shopex
 */
@TableName("d_attribute")
@Data
public class Attribute extends OrderAbleEntity{
    String name;
    @TableField("property_index")
    Integer propertyIndex;
    @TableField("product_category")
    Long productCategoryId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Attribute)) {
            return false;
        }

        Attribute attribute = (Attribute) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getName(), attribute.getName())
                .append(getPropertyIndex(), attribute.getPropertyIndex())
                .append(getProductCategoryId(), attribute.getProductCategoryId())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getName())
                .append(getPropertyIndex())
                .append(getProductCategoryId())
                .toHashCode();
    }
}
