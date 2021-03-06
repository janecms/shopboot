package com.hellojd.shopex.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.hellojd.shopex.common.LogConfig;
import com.hellojd.shopex.service.LogConfigService;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class LogConfigServiceImpl
        implements LogConfigService
{
    public List<LogConfig> getAll()
    {
        try
        {
            File localFile = new ClassPathResource("/shopxx.xml").getFile();
            Document localDocument = new SAXReader().read(localFile);
            List localList = localDocument.selectNodes("/shopxx/logConfig");
            ArrayList localArrayList = new ArrayList();
            Iterator localIterator = localList.iterator();
            while (localIterator.hasNext())
            {
                Element localElement = (Element)localIterator.next();
                String str1 = localElement.attributeValue("operation");
                String str2 = localElement.attributeValue("urlPattern");
                LogConfig localLogConfig = new LogConfig();
                localLogConfig.setOperation(str1);
                localLogConfig.setUrlPattern(str2);
                localArrayList.add(localLogConfig);
            }
            return localArrayList;
        }
        catch (Exception localException)
        {
            localException.printStackTrace();
        }
        return null;
    }
}