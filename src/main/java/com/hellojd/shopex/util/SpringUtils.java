package com.hellojd.shopex.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zhaoguoyu
 */
@Component
public final class SpringUtils
         implements BeanFactoryPostProcessor{
    /**
     * Spring应用上下文环境
     */
    private static ConfigurableListableBeanFactory beanFactory;
    private static ApplicationContext APPLICATION_CONTEXT;
    public SpringUtils(){
        System.out.println("-----------------");
    }
    public static ApplicationContext getApplicationContext()
    {
        return APPLICATION_CONTEXT;
    }

    public static Object getBean(String name){
        Assert.hasText(name);
        return beanFactory.getBean(name);
    }
    public static <T>T getBean(Class<T> type){
        return beanFactory.getBean(type);
    }
    public static <T> T getBean(String name, Class<T> type)
    {
        Assert.hasText(name);
        Assert.notNull(type);
        return (T)beanFactory.getBean(name, type);
    }
    public static String getMessage(String code, Object... args)
    {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        LocaleResolver localLocaleResolver = (LocaleResolver)getBean("localeResolver", LocaleResolver.class);
//        Locale localLocale = localLocaleResolver.resolveLocale(request);
        return beanFactory.getBean(MessageSource.class).getMessage(code, args, null);
    }


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        beanFactory=configurableListableBeanFactory;
    }
}
