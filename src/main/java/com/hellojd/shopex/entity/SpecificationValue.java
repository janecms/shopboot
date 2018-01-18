package com.hellojd.shopex.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.hellojd.shopex.bean.RefBean;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * @author Administrator
 */
@Data
@TableName("d_specification_value")
public class SpecificationValue extends  OrderAbleEntity implements  RefId{
    @NotEmpty
    @Length(max=200)
    String name;

    @Length(max=200)
    String image;

    Long specification;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof SpecificationValue)) {
            return false;
        }

        SpecificationValue that = (SpecificationValue) o;

        return new org.apache.commons.lang.builder.EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getId(), that.getId())
                .append(getName(), that.getName())
                .append(getOrder(), that.getOrder())
                .append(getImage(), that.getImage())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang.builder.HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getName())
                .append(getId())
                .append(getOrder())
                .append(getImage())
                .toHashCode();
    }
}
