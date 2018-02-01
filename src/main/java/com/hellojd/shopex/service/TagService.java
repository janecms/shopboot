package com.hellojd.shopex.service;

import com.baomidou.mybatisplus.service.IService;
import com.hellojd.shopex.entity.Tag;
import com.hellojd.shopex.enums.TagType;

import java.util.List;

/**
 *
 * @author zhaoguoyu
 * @date 2018/1/19
 */
public interface TagService  extends IService<Tag>{
    List<Tag> findList(TagType type);
    void save(Tag tag);

    void update(Tag tag);
}
