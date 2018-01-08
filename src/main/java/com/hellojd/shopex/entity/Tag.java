package com.hellojd.shopex.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.hellojd.shopex.enums.TagType;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@TableName("d_tag")
@Data
public class Tag extends OrderAbleEntity {
    private String name;
    private TagType type;
    @Length(max=200)
    private String icon;
    @Length(max=200)
    private String memo;
}
