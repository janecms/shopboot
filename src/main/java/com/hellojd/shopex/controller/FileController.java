package com.hellojd.shopex.controller;

import com.hellojd.shopex.bean.FileInfo;
import com.hellojd.shopex.common.Message;
import com.hellojd.shopex.enums.FileOrderType;
import com.hellojd.shopex.enums.FileType;
import com.hellojd.shopex.service.FileService;
import com.hellojd.shopex.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.accept.MediaTypeFileExtensionResolver;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import static com.hellojd.shopex.enums.FileType.file;
@Slf4j
@Controller
@RequestMapping("/file")
public class FileController extends BaseController{
    @Autowired
    ResourceLoader resourceLoader;
    @Autowired
    private FileService fileService;
    @Autowired
    MediaTypeFileExtensionResolver mediaTypeFileExtensionResolver;
    @RequestMapping({"/",""})
    public ResponseEntity<byte[]> render(@RequestParam("path") String location) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        final String extension = FilenameUtils.getExtension(location);
        if(StringUtils.equalsIgnoreCase(extension,"jpg")
                ||StringUtils.equalsIgnoreCase(extension,"jpeg")) {
            headers.setContentType(MediaType.IMAGE_JPEG);
        }else if(StringUtils.equalsIgnoreCase(extension,"png")) {
            headers.setContentType(MediaType.IMAGE_PNG);
        }else if(StringUtils.equalsIgnoreCase(extension,"gif")) {
            headers.setContentType(MediaType.IMAGE_GIF);
        }else{
            headers.setContentType(MediaType.TEXT_HTML);
        }
        final Resource resource = resourceLoader.getResource(location);

        if(resource.exists()){
            final InputStream is = resource.getInputStream();
            ByteArrayOutputStream baos=null;
            try {
                baos = new ByteArrayOutputStream();
                byte[] buff = new byte[100];
                int rc = 0;
                while ((rc = is.read(buff, 0, 100)) > 0) {
                    baos.write(buff, 0, rc);
                }
            } finally {
                IOUtils.closeQuietly(is);
            }
            return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.CREATED);
        }else {
            log.warn("location doesn't exists!!! {}",location);
        }
        return new ResponseEntity<byte[]>("404".getBytes(), headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = {"/upload"}, method = {RequestMethod.POST})
    public void upload(FileType fileType, MultipartFile file, HttpServletResponse response) {
        if (!this.fileService.isValid(fileType, file)) {
            JsonUtils.toJson(response, "text/html; charset=UTF-8", Message.warn("admin.upload.invalid", new Object[0]));
        } else {
            String str = this.fileService.upload(fileType, file, false);
            if (str == null) {
                JsonUtils.toJson(response, "text/html; charset=UTF-8", Message.warn("admin.upload.error", new Object[0]));
            }
            HashMap localHashMap = new HashMap();
            localHashMap.put("url", str);
            JsonUtils.toJson(response, "text/html; charset=UTF-8", localHashMap);
        }
    }
    @RequestMapping(value = {"/browser"}, method = {RequestMethod.GET})
    @ResponseBody
    public List<FileInfo> browser(String path, FileType fileType, FileOrderType orderType) {
        return this.fileService.browser(path, fileType, orderType);
    }

}
