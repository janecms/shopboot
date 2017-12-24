package com.hellojd.shopex.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;

import java.io.Serializable;
import java.util.Date;

/**
 * @author :shopex
 */
public class BaseEntity implements Serializable {
    @TableId("id")
    private String id;// ID
    @TableField("create_date")
    private Date createDate;// 创建日期
    @TableField("modify_date")
    private Date modifyDate;// 修改日期

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
