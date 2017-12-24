package com.hellojd.shopex.listener;

import com.hellojd.shopex.entity.BaseEntity;

import java.util.Date;

public class EntityListener {
    public void prePersist(BaseEntity entity)
    {
        entity.setCreateDate(new Date());
        entity.setModifyDate(new Date());
    }

    public void preUpdate(BaseEntity entity)
    {
        entity.setModifyDate(new Date());
    }
}
