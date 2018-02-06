package com.hellojd.shopex.bean;

import com.hellojd.shopex.entity.Product;
import com.hellojd.shopex.entity.Specification;
import com.hellojd.shopex.entity.SpecificationValue;
import lombok.Data;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Data
public class SpecificationBean extends Specification {
    Set<Product> products = new HashSet<>();
    List<SpecificationValue> specificationValues =new ArrayList();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof SpecificationBean)) return false;

        SpecificationBean that = (SpecificationBean) o;

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
