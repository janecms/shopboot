package com.hellojd.shopex.common;

import org.apache.commons.beanutils.converters.AbstractConverter;

public class EnumConverter
        extends AbstractConverter
{
    private final Class<?> defaultType;

    public EnumConverter(Class<?> enumClass)
    {
        this(enumClass, null);
    }

    public EnumConverter(Class<?> enumClass, Object defaultValue)
    {
        super(defaultValue);
        this.defaultType = enumClass;
    }

    @Override
    protected Class<?> getDefaultType()
    {
        return this.defaultType;
    }
    @Override
    protected Object convertToType(Class type, Object value)
    {
        String str = value.toString().trim();
        return Enum.valueOf(type, str);
    }

    @Override
    protected String convertToString(Object value)
    {
        return value.toString();
    }
}
