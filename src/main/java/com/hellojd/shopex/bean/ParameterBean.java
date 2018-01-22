package com.hellojd.shopex.bean;

import com.hellojd.shopex.entity.Parameter;
import com.hellojd.shopex.entity.ParameterGroup;
import com.hellojd.shopex.entity.RefId;
import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class ParameterBean extends Parameter {

    transient ParameterGroup parameterGroup;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParameterBean)) return false;
        ParameterBean that = (ParameterBean) o;
        return new org.apache.commons.lang.builder.EqualsBuilder()
                .appendSuper(super.equals(o))
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang.builder.HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .toHashCode();
    }
}
