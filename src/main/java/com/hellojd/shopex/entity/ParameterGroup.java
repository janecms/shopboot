package com.hellojd.shopex.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
@TableName("d_parameter_group")
@Data
public class ParameterGroup extends OrderAbleEntity{
    @NotEmpty
    @Length(max=200)
    private String name;
    @TableField("category_id")
    private Long productCategoryId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof ParameterGroup)) {
            return false;
        }

        ParameterGroup that = (ParameterGroup) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getName(), that.getName())
                .append(getProductCategoryId(), that.getProductCategoryId())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getName())
                .append(getProductCategoryId())
                .toHashCode();
    }
}
