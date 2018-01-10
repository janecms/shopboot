package com.hellojd.shopex.util;

import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class BeanUtils {
    public static void copyProperties(Object source, Object target, String[] ignoreProperties) {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");
        PropertyDescriptor[] targetPropertyDescriptors = org.springframework.beans.BeanUtils.getPropertyDescriptors(target.getClass());
        List ignoreList = ignoreProperties != null ? Arrays.asList(ignoreProperties) : null;
        for (PropertyDescriptor targetPropertyDescriptor : targetPropertyDescriptors) {
            if ((targetPropertyDescriptor.getWriteMethod() == null) || ((ignoreProperties != null) && (ignoreList.contains(targetPropertyDescriptor.getName())))) {
                continue;
            }
            PropertyDescriptor sourcePropertyDescriptor = org.springframework.beans.BeanUtils.getPropertyDescriptor(source.getClass(), targetPropertyDescriptor.getName());
            if ((sourcePropertyDescriptor == null) || (sourcePropertyDescriptor.getReadMethod() == null)) {
                continue;
            }
            try {
                Method sourceReadMethod = sourcePropertyDescriptor.getReadMethod();
                if (!Modifier.isPublic(sourceReadMethod.getDeclaringClass().getModifiers())) {
                    sourceReadMethod.setAccessible(true);
                }
                Object sourceValue = sourceReadMethod.invoke(source, new Object[0]);
                Object targetValue = sourceReadMethod.invoke(target, new Object[0]);
                Object targetCollectionObj;
                if ((sourceValue != null) && (targetValue != null) && ((targetValue instanceof Collection))) {
                    targetCollectionObj = (Collection) targetValue;
                    ((Collection) targetCollectionObj).clear();
                    ((Collection) targetCollectionObj).addAll((Collection) sourceValue);
                } else {
                    targetCollectionObj = targetPropertyDescriptor.getWriteMethod();
                    if (!Modifier.isPublic(((Method) targetCollectionObj).getDeclaringClass().getModifiers())) {
                        ((Method) targetCollectionObj).setAccessible(true);
                    }
                    ((Method) targetCollectionObj).invoke(target, new Object[]{sourceValue});
                }
            } catch (Throwable localThrowable) {
                throw new FatalBeanException("Could not copy properties from source to target", localThrowable);
            }
        }
    }
}
