package com.hellojd.shopex.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.hellojd.shopex.enums.SpecificationType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@TableName("d_specification")
@Data
@EqualsAndHashCode(exclude = {"specificationValues"})
public class Specification extends OrderAbleEntity{
    @NotEmpty
    @Length(max=200)
    String name;

    SpecificationType type;
    @Length(max=200)
    String memo;
}
