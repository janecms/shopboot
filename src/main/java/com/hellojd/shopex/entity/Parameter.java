package com.hellojd.shopex.entity;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
@TableName("d_parameter")
@Data
public class Parameter extends BaseEntity {
    @NotEmpty
    String name;
    ParameterGroup parameterGroup;
}
