package com.hellojd.shopex.interceptor;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hellojd.shopex.LogConfig;
import com.hellojd.shopex.entity.Log;
import com.hellojd.shopex.service.AdminService;
import com.hellojd.shopex.service.LogConfigService;
import com.hellojd.shopex.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.ModelAndView;

public class LogInterceptor extends HandlerInterceptorAdapter {
    private static final String[] IIIllIlI = { "password", "rePassword", "currentPassword" };
    private String[] ignoreParameters = IIIllIlI;
    @Autowired
    private LogConfigService logConfigService;
    private LogService logService;
    private static AntPathMatcher PATH_MATCHER = new AntPathMatcher();
    private AdminService adminService;
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
    {
        List localList = this.logConfigService.getAll();
        if (localList != null)
        {
            String str1 = request.getServletPath();
            Iterator localIterator1 = localList.iterator();
            while (localIterator1.hasNext())
            {
                LogConfig localLogConfig = (LogConfig)localIterator1.next();
                if (PATH_MATCHER.match(localLogConfig.getUrlPattern(), str1))
                {
                    String currentUsername = this.adminService.getCurrentUsername();
                    String operation = localLogConfig.getOperation();
                    String operator = currentUsername;
                    String content = (String)request.getAttribute(Log.LOG_CONTENT_ATTRIBUTE_NAME);
                    String ip = request.getRemoteAddr();
                    request.removeAttribute(Log.LOG_CONTENT_ATTRIBUTE_NAME);
                    StringBuffer localStringBuffer = new StringBuffer();
                    Map localMap = request.getParameterMap();
                    if (localMap != null)
                    {
                        Iterator localIterator2 = localMap.entrySet().iterator();
                        while (localIterator2.hasNext())
                        {
                           Object localObject = (Map.Entry)localIterator2.next();
                            String key = (String)((Map.Entry)localObject).getKey();
                            if (!ArrayUtils.contains(this.ignoreParameters, key))
                            {
                                String[] arrayOfString1 = (String[])((Map.Entry)localObject).getValue();
                                if (arrayOfString1 != null)
                                {
                                    String[] arrayOfString2;
                                    int j = (arrayOfString2 = arrayOfString1).length;
                                    for (int i = 0; i < j; i++)
                                    {
                                        String value = arrayOfString2[i];
                                        localStringBuffer.append(key + " = " + value + "\n");
                                    }
                                }
                            }
                        }
                    }
                    Log localObject = new Log();
                    ((Log)localObject).setOperation(operation);
                    ((Log)localObject).setOperator(operator);
                    ((Log)localObject).setContent(content);
                    ((Log)localObject).setParameter(localStringBuffer.toString());
                    ((Log)localObject).setIp(ip);
                    this.logService.insert(localObject);
                    break;
                }
            }
        }
    }
    public String[] getIgnoreParameters()
    {
        return this.ignoreParameters;
    }

    public void setIgnoreParameters(String[] ignoreParameters)
    {
        this.ignoreParameters = ignoreParameters;
    }
}
