package com.hellojd.shopex.filter;

import com.hellojd.shopex.common.Setting;
import com.hellojd.shopex.util.SettingUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SiteStatusFilter
        extends OncePerRequestFilter {
    private static final String[] DEFAULT_IGNORE_URL_PATTERNS = { "/admin/**" };
    private static AntPathMatcher PATH_MATCHER = new AntPathMatcher();
    private String[] ignoreUrlPatterns = DEFAULT_IGNORE_URL_PATTERNS;
    private String redirectUrl = "/common/site_close.jhtml";
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        Setting localSetting = SettingUtils.get();
        if (localSetting.getIsSiteEnabled().booleanValue())
        {
            filterChain.doFilter(request, response);
        }
        else
        {
            String servletPath = request.getServletPath();
            if (servletPath.equals(this.redirectUrl))
            {
                filterChain.doFilter(request, response);
                return;
            }
            if (this.ignoreUrlPatterns != null)
            {
                int j = this.ignoreUrlPatterns.length;
                for (int i = 0; i < j; i++)
                {
                    String ignoreUrl = ignoreUrlPatterns[i];
                    if (PATH_MATCHER.match(ignoreUrl, servletPath))
                    {
                        filterChain.doFilter(request, response);
                        return;
                    }
                }
            }
            response.sendRedirect(request.getContextPath() + this.redirectUrl);
        }
    }

    public String[] getIgnoreUrlPatterns()
    {
        return this.ignoreUrlPatterns;
    }

    public void setIgnoreUrlPatterns(String[] ignoreUrlPatterns)
    {
        this.ignoreUrlPatterns = ignoreUrlPatterns;
    }

    public String getRedirectUrl()
    {
        return this.redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl)
    {
        this.redirectUrl = redirectUrl;
    }
}
