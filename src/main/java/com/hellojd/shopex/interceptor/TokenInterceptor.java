package com.hellojd.shopex.interceptor;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hellojd.shopex.util.CookieUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class TokenInterceptor
        extends HandlerInterceptorAdapter
{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        String token = CookieUtils.getCookie(request, "token");
        if (request.getMethod().equalsIgnoreCase("POST"))
        {
            String str2 = request.getHeader("X-Requested-With");
            if ((str2 != null) && (str2.equalsIgnoreCase("XMLHttpRequest")))
            {
                if ((token != null) && (token.equals(request.getHeader("token")))) {
                    return true;
                }
                response.addHeader("tokenStatus", "accessDenied");
            }
            else if ((token != null) && (token.equals(request.getParameter("token"))))
            {
                return true;
            }
            if (token == null)
            {
                token = UUID.randomUUID().toString();
                CookieUtils.addCookie(request, response, "token", token);
            }
            response.sendError(403, "Bad or missing token!");
            return false;
        }
        if (token == null)
        {
            token = UUID.randomUUID().toString();
            CookieUtils.addCookie(request, response, "token", token);
        }
        request.setAttribute("token", token);
        return true;
    }
}
