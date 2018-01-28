package com.hellojd.shopex.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hellojd.shopex.util.SpringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

/**
 *
 * @author zhaoguoyu
 * @date 2018/1/28
 */
@Slf4j
public class ShopTemplateHandlerInterceptor extends HandlerInterceptorAdapter {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    request.setAttribute("templatepath","/eshop/naggy/");
    return true;
  }

}
