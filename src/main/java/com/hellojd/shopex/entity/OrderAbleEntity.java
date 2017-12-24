package com.hellojd.shopex.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;
import org.apache.commons.lang.builder.CompareToBuilder;

import javax.validation.constraints.Min;
@Data
public class OrderAbleEntity extends BaseEntity implements Comparable<OrderAbleEntity>{
    @Min(0L)
    @TableField("orders")
    private Integer order;
    @Override
    public int compareTo(OrderAbleEntity orderEntity)
    {
        return new CompareToBuilder().append(getOrder(), orderEntity.getOrder()).append(getId(), orderEntity.getId()).toComparison();
    }
}
