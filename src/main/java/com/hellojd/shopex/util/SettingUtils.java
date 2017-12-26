package com.hellojd.shopex.util;

import com.hellojd.shopex.common.CommonAttributes;
import com.hellojd.shopex.common.EnumConverter;
import com.hellojd.shopex.common.Setting;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.AbstractConverter;
import org.apache.commons.beanutils.converters.ArrayConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public final class SettingUtils {
    private static final CacheManager SETTING_MANAGER = new CacheManager();
    private static final BeanUtilsBean BEAN_UTILS_BEAN;

    static {
        LocalConvertUtilsBean local = new LocalConvertUtilsBean();
        DateConverter localDateConverter = new DateConverter();
        localDateConverter.setPatterns(CommonAttributes.DATE_PATTERNS);
        local.register(localDateConverter, Date.class);
        BEAN_UTILS_BEAN = new BeanUtilsBean(local);
    }

    public static Setting get() {
        Ehcache localEhcache = SETTING_MANAGER.getEhcache("setting");
        net.sf.ehcache.Element localElement = localEhcache.get(Setting.CACHE_KEY);
        Setting localSetting;
        if (localElement != null) {
            localSetting = (Setting) localElement.getObjectValue();
        } else {
            localSetting = new Setting();
            try {
                File localFile = new ClassPathResource("/shopxx.xml").getFile();
                Document localDocument = new SAXReader().read(localFile);
                List localList = localDocument.selectNodes("/shopxx/setting");
                Iterator localIterator = localList.iterator();
                while (localIterator.hasNext()) {
                    org.dom4j.Element localElement1 = (org.dom4j.Element) localIterator.next();
                    String str1 = localElement1.attributeValue("name");
                    String str2 = localElement1.attributeValue("value");
                    try {
                        BEAN_UTILS_BEAN.setProperty(localSetting, str1, str2);
                    } catch (IllegalAccessException localIllegalAccessException) {
                        localIllegalAccessException.printStackTrace();
                    } catch (InvocationTargetException localInvocationTargetException) {
                        localInvocationTargetException.printStackTrace();
                    }
                }
            } catch (Exception localException) {
                localException.printStackTrace();
            }
            localEhcache.put(new net.sf.ehcache.Element(Setting.CACHE_KEY, localSetting));
        }
        return localSetting;
    }

    static class LocalConvertUtilsBean
            extends ConvertUtilsBean {
        @Override
        public String convert(Object value) {
            if (value != null) {
                Class localClass = value.getClass();
                if ((localClass.isEnum()) && (super.lookup(localClass) == null)) {
                    super.register(new EnumConverter(localClass), localClass);
                } else if ((localClass.isArray()) && (localClass.getComponentType().isEnum())) {
                    if (super.lookup(localClass) == null) {
                        AbstractConverter localObject = new ArrayConverter(localClass, new EnumConverter(localClass.getComponentType()), 0);
                        ((ArrayConverter) localObject).setOnlyFirstToString(false);
                        super.register((Converter) localObject, localClass);
                    }
                    Object localObject = super.lookup(localClass);
                    return (String) ((Converter) localObject).convert(String.class, value);
                }
            }
            return super.convert(value);
        }

        @Override
        public Object convert(String value, Class clazz) {
            if ((clazz.isEnum()) && (super.lookup(clazz) == null)) {
                super.register(new EnumConverter(clazz), clazz);
            }
            return super.convert(value, clazz);
        }

        @Override
        public Object convert(String[] values, Class clazz) {
            if ((clazz.isArray()) && (clazz.getComponentType().isEnum()) && (super.lookup(clazz.getComponentType()) == null)) {
                super.register(new EnumConverter(clazz.getComponentType()), clazz.getComponentType());
            }
            return super.convert(values, clazz);
        }

        @Override
        public Object convert(Object value, Class targetType) {
            if (super.lookup(targetType) == null) {
                if (targetType.isEnum()) {
                    super.register(new EnumConverter(targetType), targetType);
                } else if ((targetType.isArray()) && (targetType.getComponentType().isEnum())) {
                    ArrayConverter localArrayConverter = new ArrayConverter(targetType, new EnumConverter(targetType.getComponentType()), 0);
                    localArrayConverter.setOnlyFirstToString(false);
                    super.register(localArrayConverter, targetType);
                }
            }
            return super.convert(value, targetType);
        }
    }
}
