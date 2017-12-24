package com.hellojd.shopex.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

@Component("springUtils")
@Lazy(false)
public final class SpringUtils
        implements DisposableBean, ApplicationContextAware {
    private static ApplicationContext APPLICATION_CONTEXT;
    @Override
    public void destroy() throws Exception {
        APPLICATION_CONTEXT=null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        APPLICATION_CONTEXT =applicationContext;
    }

    public static ApplicationContext getApplicationContext()
    {
        return APPLICATION_CONTEXT;
    }

    public static Object getBean(String name){
        Assert.hasText(name);
        return APPLICATION_CONTEXT.getBean(name);
    }
    public static <T> T getBean(String name, Class<T> type)
    {
        Assert.hasText(name);
        Assert.notNull(type);
        return (T)APPLICATION_CONTEXT.getBean(name, type);
    }
    public static String getMessage(String code, Object... args)
    {
        LocaleResolver localLocaleResolver = (LocaleResolver)getBean("localeResolver", LocaleResolver.class);
        Locale localLocale = localLocaleResolver.resolveLocale(null);
        return APPLICATION_CONTEXT.getMessage(code, args, localLocale);
    }
}
