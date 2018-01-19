package com.hellojd.shopex.repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hellojd.shopex.entity.SpecificationValue;
import com.hellojd.shopex.entity.Tag;
import com.hellojd.shopex.enums.TagType;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * @author zhaoguoyu
 */
@Repository
public interface TagRepository extends BaseMapper<Tag> {

}
