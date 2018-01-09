package com.hellojd.shopex.template;

import com.hellojd.shopex.util.FreemarkerUtils;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class BaseDirective
        implements TemplateDirectiveModel {
    private static final String IIIllIlI = "useCache";
    private static final String IIIllIll = "cacheRegion";
    private static final String IIIlllII = "id";
    private static final String IIIlllIl = "count";
    private static final String IIIllllI = "orderBy";
    private static final String IIIlllll = "\\s*,\\s*";
    private static final String IIlIIIII = "\\s+";

    protected boolean useCache(Environment paramEnvironment, Map<String, TemplateModel> paramMap) {
        Boolean localBoolean = (Boolean) FreemarkerUtils.getParameter("useCache", Boolean.class, paramMap);
        return localBoolean != null ? localBoolean.booleanValue() : true;
    }

    protected String cacheRegion(Environment paramEnvironment, Map<String, TemplateModel> paramMap) {
        String str = (String) FreemarkerUtils.getParameter("cacheRegion", String.class, paramMap);
        return str != null ? str : paramEnvironment.getTemplate().getName();
    }

    protected Long id(Map<String, TemplateModel> paramMap) {
        return (Long) FreemarkerUtils.getParameter("id", Long.class, paramMap);
    }

    protected Integer count(Map<String, TemplateModel> paramMap) {
        return (Integer) FreemarkerUtils.getParameter("count", Integer.class, paramMap);
    }


    protected void render(String paramString, Object paramObject, Environment paramEnvironment, TemplateDirectiveBody paramTemplateDirectiveBody) throws IOException, TemplateException {
        TemplateModel localTemplateModel = FreemarkerUtils.getVariable(paramString, paramEnvironment);
        FreemarkerUtils.setVariable(paramString, paramObject, paramEnvironment);
        paramTemplateDirectiveBody.render(paramEnvironment.getOut());
        FreemarkerUtils.setVariable(paramString, localTemplateModel, paramEnvironment);
    }

    protected void render(Map<String, Object> paramMap, Environment paramEnvironment, TemplateDirectiveBody paramTemplateDirectiveBody) throws IOException, TemplateException {
        HashMap localHashMap = new HashMap();
        Iterator localIterator = paramMap.keySet().iterator();
        while (localIterator.hasNext()) {
            String str = (String) localIterator.next();
            TemplateModel localTemplateModel = FreemarkerUtils.getVariable(str, paramEnvironment);
            localHashMap.put(str, localTemplateModel);
        }
        FreemarkerUtils.setVariables(paramMap, paramEnvironment);
        paramTemplateDirectiveBody.render(paramEnvironment.getOut());
        FreemarkerUtils.setVariables(localHashMap, paramEnvironment);
    }
}
