package com.hellojd.shopex.repository;

import com.hellojd.shopex.entity.Tag;

import java.util.List;

public interface TagRepository {
    public abstract List<Tag> findList(Tag.Type paramType);
}
