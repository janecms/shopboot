package com.hellojd.shopex.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
@TableName("d_parameter_group")
@Data
public class ParameterGroup extends BaseEntity{
    @NotEmpty
    @Length(max=200)
    private String name;

    @NotNull
    private ProductCategory productCategory;
    @NotEmpty
    private List<Parameter> parameters = new ArrayList();
}
