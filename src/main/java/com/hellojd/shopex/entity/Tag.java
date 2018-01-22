package com.hellojd.shopex.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.hellojd.shopex.enums.TagType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

import lombok.Data;

/**
 * 标签
 */
@TableName("d_tag")
@Data
public class Tag extends OrderAbleEntity {
    private String name;
    private TagType type;
    @Length(max=200)
    private String icon;
    @Length(max=200)
    private String memo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Tag)) return false;

        Tag tag = (Tag) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getName(), tag.getName())
                .append(getType(), tag.getType())
                .append(getIcon(), tag.getIcon())
                .append(getMemo(), tag.getMemo())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getName())
                .append(getType())
                .append(getIcon())
                .append(getMemo())
                .toHashCode();
    }
}
