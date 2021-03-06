package com.hellojd.shopex.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ExecuteTimeInterceptor
        extends HandlerInterceptorAdapter
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ExecuteTimeInterceptor.class);
    private static final String START_TIME_ATTR_TIME = ExecuteTimeInterceptor.class.getName() + ".START_TIME";
    public static final String EXECUTE_TIME_ATTRIBUTE_NAME = ExecuteTimeInterceptor.class.getName() + ".EXECUTE_TIME";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    {
        Long startTime = (Long)request.getAttribute(START_TIME_ATTR_TIME);
        if (startTime == null)
        {
            startTime = Long.valueOf(System.currentTimeMillis());
            request.setAttribute(START_TIME_ATTR_TIME, startTime);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
    {
        Long executeTime = (Long)request.getAttribute(EXECUTE_TIME_ATTRIBUTE_NAME);
        Object localObject;
        if (executeTime == null)
        {
            localObject = (Long)request.getAttribute(START_TIME_ATTR_TIME);
            Long currentTime = Long.valueOf(System.currentTimeMillis());
            executeTime = Long.valueOf(currentTime.longValue() - ((Long)localObject).longValue());
            request.setAttribute(START_TIME_ATTR_TIME, localObject);
        }
        if (modelAndView != null)
        {
            localObject = modelAndView.getViewName();
            if (!StringUtils.startsWith((String)localObject, "redirect:")) {
                modelAndView.addObject(EXECUTE_TIME_ATTRIBUTE_NAME, executeTime);
            }
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("[" + handler + "] executeTime: " + executeTime + "ms");
        }
    }
}