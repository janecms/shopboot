package com.hellojd.shopex;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.context.request.RequestContextListener;

/**
 * @author zhaoguoyu
 */
@SpringBootApplication
public class ShopexApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ShopexApplication.class, args);
	}
	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		JettyEmbeddedServletContainerFactory factory =
				new JettyEmbeddedServletContainerFactory();
		return factory;
	}

	@Override
	public void onStartup( ServletContext servletContext ) throws ServletException {
		super.onStartup( servletContext );
		servletContext.addListener( new RequestContextListener());
	}
}
