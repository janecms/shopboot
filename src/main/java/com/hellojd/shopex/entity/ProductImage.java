package com.hellojd.shopex.entity;

import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import java.beans.Transient;
import java.io.Serializable;
@Data
public class ProductImage  implements Serializable, Comparable<ProductImage> {
    @Length(max=200)
    String title;

    String source;
    String large;
    String medium;
    String thumbnail;
    @Min(0L)
    Integer order;
    transient MultipartFile file;
    @Transient
    public boolean isEmpty()
    {
        return ((getFile() == null) || (getFile().isEmpty())) && ((StringUtils.isEmpty(getSource())) || (StringUtils.isEmpty(getLarge())) || (StringUtils.isEmpty(getMedium())) || (StringUtils.isEmpty(getThumbnail())));
    }
    @Override
    public int compareTo(ProductImage productImage) {
        return new CompareToBuilder().append(getOrder(), productImage.getOrder()).toComparison();
    }
}
