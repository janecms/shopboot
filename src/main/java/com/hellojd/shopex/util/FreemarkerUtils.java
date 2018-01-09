package com.hellojd.shopex.util;
import com.hellojd.shopex.common.CommonAttributes;
import com.hellojd.shopex.common.EnumConverter;
import freemarker.core.Environment;
import freemarker.template.*;
import freemarker.template.utility.DeepUnwrap;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.converters.ArrayConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.apache.commons.beanutils.Converter;

public final class FreemarkerUtils
{
    private static final ConvertUtilsBean CONVERT_UTILS_BEAN = new FreemarkerUtils.ShopexConvertUtilsBean();

    static
    {
        DateConverter localDateConverter = new DateConverter();
        localDateConverter.setPatterns(CommonAttributes.DATE_PATTERNS);
        CONVERT_UTILS_BEAN.register(localDateConverter, Date.class);
    }

    public static String process(String template, Map<String, ?> model)
    {
        Configuration localConfiguration = null;
        ApplicationContext localApplicationContext = SpringUtils.getApplicationContext();
        if (localApplicationContext != null)
        {
            FreeMarkerConfigurer localFreeMarkerConfigurer = (FreeMarkerConfigurer)SpringUtils.getBean("freeMarkerConfigurer", FreeMarkerConfigurer.class);
            if (localFreeMarkerConfigurer != null)
                localConfiguration = localFreeMarkerConfigurer.getConfiguration();
        }
        return process(template, model, localConfiguration);
    }

    public static String process(String template, Map<String, ?> model, Configuration configuration)
    {
        if (template == null)
            return null;
        if (configuration == null)
            configuration = new Configuration();
        StringWriter localStringWriter = new StringWriter();
        try
        {
            new Template("template", new StringReader(template), configuration).process(model, localStringWriter);
        }
        catch (TemplateException localTemplateException)
        {
            localTemplateException.printStackTrace();
        }
        catch (IOException localIOException)
        {
            localIOException.printStackTrace();
        }
        return localStringWriter.toString();
    }

    public static <T> T getParameter(String name, Class<T> type, Map<String, TemplateModel> params)
    {
        Assert.hasText(name);
        Assert.notNull(type);
        Assert.notNull(params);
        TemplateModel localTemplateModel = (TemplateModel)params.get(name);
        if (localTemplateModel == null)
            return null;
        Object localObject = null;
        try {
            localObject = DeepUnwrap.unwrap(localTemplateModel);
        } catch (TemplateModelException e) {
            e.printStackTrace();
        }
        return (T)CONVERT_UTILS_BEAN.convert(localObject, type);
    }

    public static TemplateModel getVariable(String name, Environment env)
    {
        Assert.hasText(name);
        Assert.notNull(env);
        try {
            return env.getVariable(name);
        } catch (TemplateModelException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setVariable(String name, Object value, Environment env)
    {
        Assert.hasText(name);
        Assert.notNull(env);
        if ((value instanceof TemplateModel)) {
            env.setVariable(name, (TemplateModel) value);
        } else {
            try {
                env.setVariable(name, ObjectWrapper.BEANS_WRAPPER.wrap(value));
            } catch (TemplateModelException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setVariables(Map<String, Object> variables, Environment env)
    {
        Assert.notNull(variables);
        Assert.notNull(env);
        Iterator localIterator = variables.entrySet().iterator();
        while (localIterator.hasNext())
        {
            Map.Entry localEntry = (Map.Entry)localIterator.next();
            String str = (String)localEntry.getKey();
            Object localObject = localEntry.getValue();
            if ((localObject instanceof TemplateModel)) {
                env.setVariable(str, (TemplateModel) localObject);
            } else {
                try {
                    env.setVariable(str, ObjectWrapper.BEANS_WRAPPER.wrap(localObject));
                } catch (TemplateModelException e) {
                    e.printStackTrace();
                }
            }
        }
    }

   static class ShopexConvertUtilsBean extends ConvertUtilsBean{
        @Override
        public String convert(Object value)
        {
            if (value != null)
            {
                Class localClass = value.getClass();
                if ((localClass.isEnum()) && (super.lookup(localClass) == null))
                {
                    super.register(new EnumConverter(localClass), localClass);
                }
                else if ((localClass.isArray()) && (localClass.getComponentType().isEnum()))
                {
                    if (super.lookup(localClass) == null)
                    {
                       Object localObject = new ArrayConverter(localClass, new EnumConverter(localClass.getComponentType()), 0);
                        ((ArrayConverter)localObject).setOnlyFirstToString(false);
                        super.register((Converter)localObject, localClass);
                    }
                    Object localObject = super.lookup(localClass);
                    return (String)((Converter)localObject).convert(String.class, value);
                }
            }
            return (String)super.convert(value);
        }

        @Override
        public Object convert(String value, Class clazz)
        {
            if ((clazz.isEnum()) && (super.lookup(clazz) == null))
                super.register(new EnumConverter(clazz), clazz);
            return super.convert(value, clazz);
        }

        @Override
        public Object convert(String[] values, Class clazz)
        {
            if ((clazz.isArray()) && (clazz.getComponentType().isEnum()) && (super.lookup(clazz.getComponentType()) == null))
                super.register(new EnumConverter(clazz.getComponentType()), clazz.getComponentType());
            return super.convert(values, clazz);
        }

        @Override
        public Object convert(Object value, Class targetType)
        {
            if (super.lookup(targetType) == null)
                if (targetType.isEnum())
                {
                    super.register(new EnumConverter(targetType), targetType);
                }
                else if ((targetType.isArray()) && (targetType.getComponentType().isEnum()))
                {
                    ArrayConverter localArrayConverter = new ArrayConverter(targetType, new EnumConverter(targetType.getComponentType()), 0);
                    localArrayConverter.setOnlyFirstToString(false);
                    super.register(localArrayConverter, targetType);
                }
            return super.convert(value, targetType);
        }
    }
}