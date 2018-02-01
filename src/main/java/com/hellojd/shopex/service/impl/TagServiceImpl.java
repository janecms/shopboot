package com.hellojd.shopex.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hellojd.shopex.enums.TagType;
import com.hellojd.shopex.util.BeanUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hellojd.shopex.entity.Tag;
import com.hellojd.shopex.repository.TagRepository;
import com.hellojd.shopex.service.TagService;

import java.util.List;

/**
 *
 * @author zhaoguoyu
 * @date 2018/1/19
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagRepository, Tag> implements TagService {
    @Override
    public List<Tag> findList(TagType type) {
        final Tag tag = new Tag();
        tag.setType(type);
        final EntityWrapper ew = new EntityWrapper(tag);
        return baseMapper.selectList(ew);
    }

    @Override
    public void save(Tag tag) {
        this.baseMapper.insert(tag);
    }

    @Override
    public void update(Tag tag) {
        final Tag po = this.baseMapper.selectById(tag.getId());
        BeanUtils.copyProperties(tag,po,new String[] { "type", "articles", "products" });
        this.baseMapper.updateById(po);
    }
}
