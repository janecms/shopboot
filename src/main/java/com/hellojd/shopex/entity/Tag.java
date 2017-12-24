package com.hellojd.shopex.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.hibernate.validator.constraints.Length;
@TableName("d_tag")
public class Tag extends OrderAbleEntity {
    public enum Type
    {
        article,  product;
    }

    private String name;
    private Type type;
    @Length(max=200)
    private String icon;
    @Length(max=200)
    private String memo;
}
