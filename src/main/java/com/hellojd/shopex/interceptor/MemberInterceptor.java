package com.hellojd.shopex.interceptor;

import com.hellojd.shopex.Principal;
import com.hellojd.shopex.entity.Member;
import com.hellojd.shopex.service.MemberService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

public class MemberInterceptor
        extends HandlerInterceptorAdapter {
    private static final String DEFAULT_LOGIN_URL = "/login.jhtml";
    private String loginUrl = DEFAULT_LOGIN_URL;
    @Value("${url_escaping_charset}")
    private String charset;
    private MemberService memberService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        HttpSession localHttpSession = request.getSession();
        Principal localPrincipal = (Principal)localHttpSession.getAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME);
        if (localPrincipal != null) {
            return true;
        }
        String str1 = request.getHeader("X-Requested-With");
        if ((str1 != null) && (str1.equalsIgnoreCase("XMLHttpRequest")))
        {
            response.addHeader("loginStatus", "accessDenied");
            response.sendError(403);
            return false;
        }
        if (request.getMethod().equalsIgnoreCase("GET"))
        {
            String str2 = request.getQueryString() != null ? request.getRequestURI() + "?" + request.getQueryString() : request.getRequestURI();
            response.sendRedirect(request.getContextPath() + this.loginUrl + "?" + "redirectUrl" + "=" + URLEncoder.encode(str2, this.charset));
        }
        else
        {
            response.sendRedirect(request.getContextPath() + this.loginUrl);
        }
        return false;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
    {
        if (modelAndView != null)
        {
            String str = modelAndView.getViewName();
            if (!StringUtils.startsWith(str, "redirect:")) {
                modelAndView.addObject("member", this.memberService.getCurrent());
            }
        }
    }
    public String getLoginUrl()
    {
        return this.loginUrl;
    }

    public void setLoginUrl(String loginUrl)
    {
        this.loginUrl = loginUrl;
    }
}
