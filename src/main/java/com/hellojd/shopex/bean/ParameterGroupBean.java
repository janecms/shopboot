package com.hellojd.shopex.bean;

import com.hellojd.shopex.entity.ParameterGroup;
import com.hellojd.shopex.entity.ProductCategory;
import lombok.Data;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;
/**
 * 浅比较
 * @author Administrator
 */
@Data
public class ParameterGroupBean extends ParameterGroup {
    private ProductCategory productCategory;
    private List<ParameterBean> parameters = new ArrayList();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof ParameterGroupBean)) {
            return false;
        }

        ParameterGroupBean that = (ParameterGroupBean) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .toHashCode();
    }
}
