package com.hellojd.shopex.bean;

import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.hellojd.shopex.entity.*;
import com.hellojd.shopex.util.FreemarkerUtils;
import lombok.Data;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

import java.beans.Transient;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.baomidou.mybatisplus.toolkit.IdWorker.getId;

@Data
public class ProductBean extends Product {
    ProductCategoryBean productCategory;
    private Brand brand;
    private Map<ParameterBean, String> parameterValue;
    private Map<AttributeBean, String> attributeValueMap;
    Set<Tag> tags;
    Set<Specification> specifications;
    Set<SpecificationValue> specificationValues;
    Map<MemberRank, BigDecimal> memberPrice;

    @Transient
    public Integer getAvailableStock()
    {
        Integer localInteger = null;
        if ((getStock() != null) && (getAllocatedStock() != null))
        {
            localInteger = Integer.valueOf(getStock().intValue() - getAllocatedStock().intValue());
            if (localInteger.intValue() < 0) {
                localInteger = Integer.valueOf(0);
            }
        }
        return localInteger;
    }

    @Transient
    public Boolean getIsOutOfStock()
    {
        if ((getStock() != null) && (getAllocatedStock() != null) && (getAllocatedStock().intValue() >= getStock().intValue())) {
            return Boolean.valueOf(true);
        }
        return Boolean.valueOf(false);
    }

   public String getAttributeValue(AttributeBean attributeBean){
        if(this.attributeValueMap==null){
            return "";
        }
        return attributeValueMap.get(attributeBean);
    }
}
