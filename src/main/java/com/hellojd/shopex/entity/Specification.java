package com.hellojd.shopex.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;
@TableName("d_specification")
@Data
public class Specification extends OrderAbleEntity{
    enum  Type{
        text,  image;
    }
    @NotEmpty
    @Length(max=200)
    String name;

    Type type;
    @Length(max=200)
    String memo;

    List<SpecificationValue> specificationValues =new ArrayList();
}
