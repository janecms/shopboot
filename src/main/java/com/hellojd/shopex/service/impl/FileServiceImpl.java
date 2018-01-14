package com.hellojd.shopex.service.impl;

import com.hellojd.shopex.bean.FileInfo;
import com.hellojd.shopex.common.Setting;
import com.hellojd.shopex.enums.FileOrderType;
import com.hellojd.shopex.enums.FileType;
import com.hellojd.shopex.plugin.StoragePlugin;
import com.hellojd.shopex.service.FileService;
import com.hellojd.shopex.service.PluginService;
import com.hellojd.shopex.util.FreemarkerUtils;
import com.hellojd.shopex.util.SettingUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.*;

import org.springframework.core.task.TaskExecutor;

/**
 * @author zgy
 */
@Service
public class FileServiceImpl implements FileService, ServletContextAware {
    @Autowired
    private TaskExecutor taskExecutor;
    private ServletContext servletContext;
    @Autowired
    private PluginService pluginService;
    @Override
    public boolean isValid(FileType fileType, MultipartFile multipartFile) {
        if (multipartFile == null) {
            return false;
        }
        Setting setting = SettingUtils.get();
        if ((setting.getUploadMaxSize() != null) && (setting.getUploadMaxSize().intValue() != 0) && (multipartFile.getSize() > setting.getUploadMaxSize().intValue() * 1024L * 1024L)) {
            return false;
        }
        String[] extensions;
        if (fileType == FileType.flash) {
            extensions = setting.getUploadFlashExtensions();
        } else if (fileType == FileType.media) {
            extensions = setting.getUploadMediaExtensions();
        } else if (fileType == FileType.file) {
            extensions = setting.getUploadFileExtensions();
        } else {
            extensions = setting.getUploadImageExtensions();
        }
        if (ArrayUtils.isNotEmpty(extensions)) {
            return FilenameUtils.isExtension(multipartFile.getOriginalFilename(), extensions);
        }
        return false;
    }

    class UploadTask implements Runnable {
        public UploadTask(StoragePlugin storagePlugin, String path, File file, String contentType) {
            this.storagePlugin = storagePlugin;
            this.path = path;
            this.file = file;
            this.contentType = contentType;
        }

        StoragePlugin storagePlugin;
        String path;
        File file;
        String contentType;

        @Override
        public void run() {
            try {
                this.storagePlugin.upload(path, this.file, this.contentType);
            } finally {
                FileUtils.deleteQuietly(this.file);
            }
        }
    }

    private void asyncUpload(StoragePlugin storagePlugin, String path, File file, String contentType) {
        this.taskExecutor.execute(new UploadTask(storagePlugin, path, file, contentType));
    }

    @Override
    public List<FileInfo> browser(String path, FileType fileType, FileOrderType orderType) {
        if (path != null) {
            if (!path.startsWith("/")) {
                path = "/" + path;
            }
            if (!path.endsWith("/")) {
                path = path + "/";
            }
        } else {
            path = "/";
        }
        Setting setting = SettingUtils.get();
        String uploadPath;
        if (fileType == FileType.flash) {
            uploadPath = setting.getFlashUploadPath();
        } else if (fileType == FileType.media) {
            uploadPath = setting.getMediaUploadPath();
        } else if (fileType == FileType.file) {
            uploadPath = setting.getFileUploadPath();
        } else {
            uploadPath = setting.getImageUploadPath();
        }
        String fullPath = StringUtils.substringBefore(uploadPath, "${");
        fullPath = StringUtils.substringBeforeLast(fullPath, "/") + path;
        List<FileInfo> fileInfos = new ArrayList();
        if (fullPath.indexOf("..") >= 0) {
            return fileInfos;
        }
        Iterator pluginIterator = this.pluginService.getStoragePlugins(true).iterator();
        if (pluginIterator.hasNext()) {
            StoragePlugin storagePlugin = (StoragePlugin) pluginIterator.next();
            fileInfos = storagePlugin.browser(fullPath);
        }
        if (orderType == FileOrderType.size) {
            Collections.sort((List) fileInfos, new SizeComparator());
        } else if (orderType == FileOrderType.type) {
            Collections.sort((List) fileInfos, new TypeComparator());
        } else {
            Collections.sort((List) fileInfos, new NameComparator());
        }
        return (List<FileInfo>) fileInfos;
    }

    @Override
    public String upload(FileType fileType, MultipartFile multipartFile, boolean async) {
        if (multipartFile == null) {
            return null;
        }
        Setting setting = SettingUtils.get();
        String uploadPath;
        if (fileType == FileType.flash) {
            uploadPath = setting.getFlashUploadPath();
        } else if (fileType == FileType.media) {
            uploadPath = setting.getMediaUploadPath();
        } else if (fileType == FileType.file) {
            uploadPath = setting.getFileUploadPath();
        } else {
            uploadPath = setting.getImageUploadPath();
        }
        try {
            HashMap localHashMap = new HashMap();
            localHashMap.put("uuid", UUID.randomUUID().toString());
            String str2 = FreemarkerUtils.process(uploadPath, localHashMap);
            String path = str2 + UUID.randomUUID() + "." + FilenameUtils.getExtension(multipartFile.getOriginalFilename());
            Iterator storagePlugins = this.pluginService.getStoragePlugins(true).iterator();
            if (storagePlugins.hasNext()) {
                StoragePlugin localStoragePlugin = (StoragePlugin) storagePlugins.next();
                File file = new File(System.getProperty("java.io.tmpdir") + "/upload_" + UUID.randomUUID() + ".tmp");
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                multipartFile.transferTo(file);
                if (async) {
                    asyncUpload(localStoragePlugin, path, file, multipartFile.getContentType());
                } else {
                    try {
                        localStoragePlugin.upload(path, file, multipartFile.getContentType());
                    } finally {
                        FileUtils.deleteQuietly(file);
                    }
                }
                return localStoragePlugin.getUrl(path);
            }
        } catch (Exception localException1) {
            localException1.printStackTrace();
        }
        return null;
    }

    @Override
    public String upload(FileType fileType, MultipartFile multipartFile) {
        return upload(fileType, multipartFile, false);
    }

    @Override
    public String uploadLocal(FileType fileType, MultipartFile multipartFile) {
        if (multipartFile == null) {
            return null;
        }
        Setting localSetting = SettingUtils.get();
        String pathTemplate;
        if (fileType == FileType.flash) {
            pathTemplate = localSetting.getFlashUploadPath();
        } else if (fileType == FileType.media) {
            pathTemplate = localSetting.getMediaUploadPath();
        } else if (fileType == FileType.file) {
            pathTemplate = localSetting.getFileUploadPath();
        } else {
            pathTemplate = localSetting.getImageUploadPath();
        }
        try {
            HashMap dataModel = new HashMap();
            dataModel.put("uuid", UUID.randomUUID().toString());
            String path = FreemarkerUtils.process(pathTemplate, dataModel);
            String fullPath = path + UUID.randomUUID() + "." + FilenameUtils.getExtension(multipartFile.getOriginalFilename());
            File localFile = new File(this.servletContext.getRealPath(fullPath));
            if (!localFile.getParentFile().exists()) {
                localFile.getParentFile().mkdirs();
            }
            multipartFile.transferTo(localFile);
            return fullPath;
        } catch (Exception localException1) {
            localException1.printStackTrace();
        }
        return null;
    }


    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    class NameComparator implements Comparator<FileInfo> {
        @Override
        public int compare(FileInfo fileInfos1, FileInfo fileInfos2) {
            return new CompareToBuilder().append(!fileInfos1.getIsDirectory().booleanValue(), !fileInfos2.getIsDirectory().booleanValue()).append(fileInfos1.getName(), fileInfos2.getName()).toComparison();
        }
    }

    class SizeComparator implements Comparator<FileInfo> {
        @Override
        public int compare(FileInfo fileInfos1, FileInfo fileInfos2) {
            return new CompareToBuilder().append(!fileInfos1.getIsDirectory().booleanValue(), !fileInfos2.getIsDirectory().booleanValue()).append(fileInfos1.getSize(), fileInfos2.getSize()).toComparison();
        }
    }

    class TypeComparator implements Comparator<FileInfo> {
        @Override
        public int compare(FileInfo fileInfos1, FileInfo fileInfos2) {
            return new CompareToBuilder().append(!fileInfos1.getIsDirectory().booleanValue(), !fileInfos2.getIsDirectory().booleanValue()).append(FilenameUtils.getExtension(fileInfos1.getName()), FilenameUtils.getExtension(fileInfos2.getName())).toComparison();
        }
    }
}
