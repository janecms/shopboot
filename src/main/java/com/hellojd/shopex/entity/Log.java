package com.hellojd.shopex.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
@TableName("log")
@Data
public class Log extends BaseEntity {
    public static final String LOG_CONTENT_ATTRIBUTE_NAME = Log.class.getName() + ".CONTENT";
    private String operation;
    private String operator;
    private String content;
    private String parameter;
    private String ip;
}
