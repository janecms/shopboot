package com.hellojd.shopex.plugin;

import com.hellojd.shopex.bean.FileInfo;
import com.hellojd.shopex.common.Setting;
import com.hellojd.shopex.util.SettingUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
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
@Slf4j
@Component("filePlugin")
public class FilePlugin extends StoragePlugin implements ResourceLoaderAware {
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
        final Resource resource = this.resourceLoader.getResource(path);
        try
        {
            FileUtils.moveFile(file, resource.getFile());
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
        List files = new ArrayList();
        final Resource resource = this.resourceLoader.getResource(path);
        final String realPath;
        try {
            realPath = resource.getFile().getAbsolutePath();
            File browserFile = new File(realPath);
            if ((browserFile.exists()) && (browserFile.isDirectory())) {
                for (File file : browserFile.listFiles()) {
                    FileInfo localFileInfo = new FileInfo();
                    localFileInfo.setName(file.getName());
                    localFileInfo.setUrl(setting.getSiteUrl() + path + file.getName());
                    localFileInfo.setIsDirectory(Boolean.valueOf(file.isDirectory()));
                    localFileInfo.setSize(Long.valueOf(file.length()));
                    localFileInfo.setLastModified(new Date(file.lastModified()));
                    files.add(localFileInfo);
                }
            }
        } catch (IOException e) {
            log.error("文件路径不存在:{}",path);
            e.printStackTrace();
        }
        return files;
    }



    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
         this.resourceLoader=resourceLoader;
    }

    ResourceLoader resourceLoader;
}
