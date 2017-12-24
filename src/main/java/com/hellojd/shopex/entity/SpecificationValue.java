package com.hellojd.shopex.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@TableName("d_specification_value")
public class SpecificationValue extends  OrderAbleEntity{
    @NotEmpty
    @Length(max=200)
    String name;

    @Length(max=200)
    String image;

    Specification specification;
}
