package com.hellojd.shopex.util;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hellojd.shopex.common.Setting;
import com.hellojd.shopex.common.ShopxxSettings;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;
public final class CookieUtils
{
    public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, Integer maxAge, String path, String domain, Boolean secure)
    {
        Assert.notNull(request);
        Assert.notNull(response);
        Assert.hasText(name);
        try
        {
            name = URLEncoder.encode(name, "UTF-8");
            value = URLEncoder.encode(value, "UTF-8");
            Cookie localCookie = new Cookie(name, value);
            if (maxAge != null) {
                localCookie.setMaxAge(maxAge.intValue());
            }
            if (StringUtils.isNotEmpty(path)) {
                localCookie.setPath(path);
            }
            if (StringUtils.isNotEmpty(domain)) {
                localCookie.setDomain(domain);
            }
            if (secure != null) {
                localCookie.setSecure(secure.booleanValue());
            }
            response.addCookie(localCookie);
        }
        catch (UnsupportedEncodingException localUnsupportedEncodingException1)
        {
            localUnsupportedEncodingException1.printStackTrace();
        }
    }

    public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, Integer maxAge)
    {
        final ShopxxSettings settings = SpringUtils.getBean(ShopxxSettings.class);
        addCookie(request, response, name, value, maxAge, settings.getCookiePath(), settings.getCookieDomain(), null);
    }

    public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value)
    {
        final ShopxxSettings settings = SpringUtils.getBean(ShopxxSettings.class);
        addCookie(request, response, name, value, null, settings.getCookiePath(), settings.getCookieDomain(), null);
    }

    public static String getCookie(HttpServletRequest request, String name)
    {
        Assert.notNull(request);
        Assert.hasText(name);
        Cookie[] arrayOfCookie1 = request.getCookies();
        if (arrayOfCookie1 != null) {
            try
            {
                name = URLEncoder.encode(name, "UTF-8");
                Cookie[] arrayOfCookie2;
                int j = (arrayOfCookie2 = arrayOfCookie1).length;
                for (int i = 0; i < j; i++)
                {
                    Cookie localCookie = arrayOfCookie2[i];
                    if (name.equals(localCookie.getName())) {
                        return URLDecoder.decode(localCookie.getValue(), "UTF-8");
                    }
                }
            }
            catch (UnsupportedEncodingException localUnsupportedEncodingException1)
            {
                localUnsupportedEncodingException1.printStackTrace();
            }
        }
        return null;
    }

    public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String name, String path, String domain)
    {
        Assert.notNull(request);
        Assert.notNull(response);
        Assert.hasText(name);
        try
        {
            name = URLEncoder.encode(name, "UTF-8");
            Cookie localCookie = new Cookie(name, null);
            localCookie.setMaxAge(0);
            if (StringUtils.isNotEmpty(path)) {
                localCookie.setPath(path);
            }
            if (StringUtils.isNotEmpty(domain)) {
                localCookie.setDomain(domain);
            }
            response.addCookie(localCookie);
        }
        catch (UnsupportedEncodingException localUnsupportedEncodingException1)
        {
            localUnsupportedEncodingException1.printStackTrace();
        }
    }

    public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String name)
    {
        ShopxxSettings settings = SettingUtils.get();
        removeCookie(request, response, name, settings.getCookiePath(), settings.getCookieDomain());
    }
}
