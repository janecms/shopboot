package com.hellojd.shopex.bean;

import java.io.Serializable;

/**
 * 父子关系Bean
 */
public interface RefBean {
    //PK
    Serializable getParentId();
    //关联FK
    Serializable getChildId();
}
