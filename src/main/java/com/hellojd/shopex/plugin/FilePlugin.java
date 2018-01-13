package com.hellojd.shopex.plugin;

import com.hellojd.shopex.bean.FileInfo;
import com.hellojd.shopex.common.Setting;
import com.hellojd.shopex.util.SettingUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 */
@Service
public class FilePlugin extends StoragePlugin implements ServletContextAware {
    private ServletContext servletContext;
    @Override
    public String getName() {
        return "本地文件存储";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public String getAuthor() {
        return "shop++";
    }

    @Override
    public String getSiteUrl() {
        return "";
    }

    @Override
    public String getInstallUrl() {
        return null;
    }

    @Override
    public String getUninstallUrl() {
        return null;
    }

    @Override
    public String getSettingUrl() {
        return "file/setting.jhtml";
    }

    @Override
    public void upload(String path, File file, String contentType) {
        File localFile = new File(this.servletContext.getRealPath(path));
        try
        {
            FileUtils.moveFile(file, localFile);
        }
        catch (IOException localIOException)
        {
            localIOException.printStackTrace();
        }
    }

    @Override
    public String getUrl(String path) {
        Setting localSetting = SettingUtils.get();
        return localSetting.getSiteUrl() + path;
    }

    @Override
    public List<FileInfo> browser(String path) {
        Setting setting = SettingUtils.get();
        List localArrayList = new ArrayList();
        File browserFile = new File(this.servletContext.getRealPath(path));
        if ((browserFile.exists()) && (browserFile.isDirectory())) {
            for (File file : browserFile.listFiles()) {
                FileInfo localFileInfo = new FileInfo();
                localFileInfo.setName(file.getName());
                localFileInfo.setUrl(setting.getSiteUrl() + path + file.getName());
                localFileInfo.setIsDirectory(Boolean.valueOf(file.isDirectory()));
                localFileInfo.setSize(Long.valueOf(file.length()));
                localFileInfo.setLastModified(new Date(file.lastModified()));
                localArrayList.add(localFileInfo);
            }
        }
        return localArrayList;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext=servletContext;
    }
}
