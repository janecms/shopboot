package com.hellojd.shopex.bean;

import lombok.Data;

import java.util.Date;
/**
 * @author zgy
 */
@Data
public class FileInfo {
    String name;
    String url;
    Boolean isDirectory;
    Long size;
    Date lastModified;

}
