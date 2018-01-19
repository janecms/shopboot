package com.hellojd.shopex.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.springframework.data.domain.Auditable;

/**
 * @author :shopex
 */
public class BaseEntity implements Auditable<String,Long>, Serializable {
  @TableId("id")
  // ID
  protected Long id;
  @TableField("create_date")
  // 创建日期
  protected Date createDate;
  @TableField("modify_date")
  // 修改日期
  protected Date modifyDate;

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public boolean isNew() {
    return id==null;
  }

  public void setId(Long id) {
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

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (this == obj) {
      return true;
    }
    if (!BaseEntity.class.isAssignableFrom(obj.getClass())) {
      return false;
    }
    BaseEntity localBaseEntity = (BaseEntity) obj;
    return getId() != null ? getId().equals(localBaseEntity.getId()) : false;
  }

  @Override
  public int hashCode() {
    int i = 17;
    i += (getId() == null ? 0 : getId().hashCode() * 31);
    return i;
  }

  @Override
  public String getCreatedBy() {
    return null;
  }

  @Override
  public void setCreatedBy(String createdBy) {

  }

  @Override
  public DateTime getCreatedDate() {
    return new DateTime(this.createDate);
  }

  @Override
  public void setCreatedDate(DateTime creationDate) {
    this.createDate=creationDate.toDate();
  }

  @Override
  public String getLastModifiedBy() {
    return null;
  }

  @Override
  public void setLastModifiedBy(String lastModifiedBy) {

  }

  @Override
  public DateTime getLastModifiedDate() {
    return new DateTime(this.modifyDate);
  }

  @Override
  public void setLastModifiedDate(DateTime lastModifiedDate) {
    this.modifyDate=lastModifiedDate.toDate();
  }


}
