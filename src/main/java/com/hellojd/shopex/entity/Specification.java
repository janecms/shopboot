package com.hellojd.shopex.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.hellojd.shopex.enums.SpecificationType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Specification)) return false;

        Specification that = (Specification) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getName(), that.getName())
                .append(getType(), that.getType())
                .append(getMemo(), that.getMemo())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getName())
                .append(getType())
                .append(getMemo())
                .toHashCode();
    }
}
