package com.hellojd.shopex.entity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotEmpty;
/**
 * @author Administrator
 */
@TableName("d_parameter")
@Data
public class Parameter extends OrderAbleEntity implements RefId{
    @NotEmpty
    String name;
    @TableField("parameter_group")
    private Long parameterGroupId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Parameter)) return false;

        Parameter parameter = (Parameter) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getName(), parameter.getName())
                .append(getParameterGroupId(), parameter.getParameterGroupId())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getName())
                .append(getParameterGroupId())
                .toHashCode();
    }
}
