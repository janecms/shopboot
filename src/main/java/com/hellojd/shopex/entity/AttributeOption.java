package com.hellojd.shopex.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;
@TableName("d_attribute_option")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttributeOption implements Serializable {
    Long attribute;
    String options;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof AttributeOption)) {
            return false;
        }

        AttributeOption that = (AttributeOption) o;

        return new EqualsBuilder()
//                .appendSuper(super.equals(o))
                .append(getAttribute(), that.getAttribute())
                .append(getOptions(), that.getOptions())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
//                .appendSuper(super.hashCode())
                .append(getAttribute())
                .append(getOptions())
                .toHashCode();
    }
}
