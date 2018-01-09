package com.hellojd.shopex.controller;

import com.hellojd.shopex.common.Message;
import com.hellojd.shopex.common.Setting;
import com.hellojd.shopex.common.web.DateEditor;
import com.hellojd.shopex.common.web.FlashMessageDirective;
import com.hellojd.shopex.entity.Log;
import com.hellojd.shopex.util.SettingUtils;
import com.hellojd.shopex.util.SpringUtils;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

public class BaseController {
    protected static final String ADMIN_COMMON_ERROR = "/admin/common/error";
    protected static final Message ERROR = Message.error("admin.message.error", new Object[0]);
    protected static final Message SUCCESS = Message.success("admin.message.success", new Object[0]);
    private static final String CONSTRAINT_VIOLATIONS = "constraintViolations";

    @Resource(name="validator")
    private Validator validator;

    @InitBinder
    protected void initBinder(WebDataBinder paramWebDataBinder)
    {
        paramWebDataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        paramWebDataBinder.registerCustomEditor(Date.class, new DateEditor(true));
    }

    protected boolean validate(Object paramObject, Class<?>[] paramArrayOfClass)
    {
        Set localSet = this.validator.validate(paramObject, paramArrayOfClass);
        if (localSet.isEmpty()){
            return true;
        }
        RequestAttributes localRequestAttributes = RequestContextHolder.currentRequestAttributes();
        localRequestAttributes.setAttribute("constraintViolations", localSet, 0);
        return false;
    }

    protected boolean validateValue(Class<?> paramClass, String paramString, Object paramObject, Class<?>[] paramArrayOfClass)
    {
        Set localSet = this.validator.validateValue(paramClass, paramString, paramObject, paramArrayOfClass);
        if (localSet.isEmpty()) {
            return true;
        }
        RequestAttributes localRequestAttributes = RequestContextHolder.currentRequestAttributes();
        localRequestAttributes.setAttribute("constraintViolations", localSet, 0);
        return false;
    }

    protected String unit(BigDecimal paramBigDecimal, boolean currencySign, boolean currencyUnit)
    {
        Setting localSetting = SettingUtils.get();
        String str = localSetting.setScale(paramBigDecimal).toString();
        if (currencySign)
            str = localSetting.getCurrencySign() + str;
        if (currencyUnit)
            str = str + localSetting.getCurrencyUnit();
        return str;
    }

    protected String logContent(String paramString, Object[] paramArrayOfObject)
    {
        return SpringUtils.getMessage(paramString, paramArrayOfObject);
    }

    protected void addAttribute(RedirectAttributes paramRedirectAttributes, Message paramMessage)
    {
        if ((paramRedirectAttributes != null) && (paramMessage != null)) {
            paramRedirectAttributes.addFlashAttribute(FlashMessageDirective.FLASH_MESSAGE_ATTRIBUTE_NAME, paramMessage);
        }
    }


    protected void logContent(String paramString)
    {
        if (paramString != null)
        {
            RequestAttributes localRequestAttributes = RequestContextHolder.currentRequestAttributes();
            localRequestAttributes.setAttribute(Log.LOG_CONTENT_ATTRIBUTE_NAME, paramString, 0);
        }
    }
}
