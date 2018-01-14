package com.hellojd.shopex.controller;

import com.hellojd.shopex.bean.FileInfo;
import com.hellojd.shopex.common.Message;
import com.hellojd.shopex.enums.FileOrderType;
import com.hellojd.shopex.enums.FileType;
import com.hellojd.shopex.service.FileService;
import com.hellojd.shopex.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
@Controller
@RequestMapping("/file")
public class FileController extends BaseController {
    @Autowired
    private FileService fileService;

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
