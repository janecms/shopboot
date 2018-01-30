package com.hellojd.shopex.bean;

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
    ProductCategory productCategory;
    private Brand brand;
    private Map<Parameter, String> parameterValue;
    Set<Tag> tags;
    Set<Specification> specifications;
    Set<SpecificationValue> specificationValues;
    Map<MemberRank, BigDecimal> memberPrice;

    @Transient
    public String getAttributeValue(Attribute attribute) {
        if ((attribute != null) && (attribute.getPropertyIndex() != null)) {
            try {
                String str = "attributeValue" + attribute.getPropertyIndex();
                return (String) PropertyUtils.getProperty(this, str);
            } catch (IllegalAccessException localIllegalAccessException1) {
                localIllegalAccessException1.printStackTrace();
            } catch (InvocationTargetException localInvocationTargetException1) {
                localInvocationTargetException1.printStackTrace();
            } catch (NoSuchMethodException localNoSuchMethodException1) {
                localNoSuchMethodException1.printStackTrace();
            }
        }
        return null;
    }

    @Transient
    public void setAttributeValue(AttributeBean attribute, String value) {
        if ((attribute != null) && (attribute.getPropertyIndex() != null)) {
            if (StringUtils.isEmpty(value)) {
                value = null;
            }
            if ((value == null) || ((attribute.getOptions() != null) && (attribute.getOptions().contains(value)))) {
                try {
                    String str = "attributeValue" + attribute.getPropertyIndex();
                    PropertyUtils.setProperty(this, str, value);
                } catch (IllegalAccessException localIllegalAccessException1) {
                    localIllegalAccessException1.printStackTrace();
                } catch (InvocationTargetException localInvocationTargetException1) {
                    localInvocationTargetException1.printStackTrace();
                } catch (NoSuchMethodException localNoSuchMethodException1) {
                    localNoSuchMethodException1.printStackTrace();
                }
            }
        }
    }
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
}
