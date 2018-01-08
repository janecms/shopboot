package com.hellojd.shopex.repository;

import com.hellojd.shopex.entity.Tag;
import com.hellojd.shopex.enums.TagType;

import java.util.List;

public interface TagRepository {
    public abstract List<Tag> findList(TagType paramType);
}
