package com.hellojd.shopex.service;

import com.hellojd.shopex.bean.FileInfo;
import com.hellojd.shopex.enums.FileOrderType;
import com.hellojd.shopex.enums.FileType;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author zgy
 */
public interface FileService {
    public abstract boolean isValid(FileType paramFileType, MultipartFile paramMultipartFile);

    public abstract String upload(FileType paramFileType, MultipartFile paramMultipartFile, boolean paramBoolean);

    public abstract String upload(FileType paramFileType, MultipartFile paramMultipartFile);

    public abstract String uploadLocal(FileType paramFileType, MultipartFile paramMultipartFile);

    public abstract List<FileInfo> browser(String paramString, FileType paramFileType, FileOrderType paramOrderType);
}
