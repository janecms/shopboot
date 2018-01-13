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
@EqualsAndHashCode
@Data
@TableName("d_specification_value")
public class SpecificationValue extends  OrderAbleEntity{
    @NotEmpty
    @Length(max=200)
    String name;

    @Length(max=200)
    String image;

    Long specification;
}
