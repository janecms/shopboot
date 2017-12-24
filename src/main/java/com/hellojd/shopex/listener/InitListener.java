package com.hellojd.shopex.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

public class InitListener implements ApplicationListener<ContextRefreshedEvent>,ServletContextAware {
    private ServletContext servletContext;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext=servletContext;
    }
}
