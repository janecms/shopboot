package com.hellojd.shopex.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.validation.constraints.Min;
@Data
public class OrderAbleEntity extends BaseEntity implements Comparable<OrderAbleEntity>{
    @Min(0L)
    @TableField("orders")
    protected Integer order;
    @Override
    public int compareTo(OrderAbleEntity orderEntity)
    {
        return new CompareToBuilder().append(getOrder(), orderEntity.getOrder()).append(getId(), orderEntity.getId()).toComparison();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof OrderAbleEntity)) return false;

        OrderAbleEntity that = (OrderAbleEntity) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getOrder(), that.getOrder())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getOrder())
                .toHashCode();
    }
}
